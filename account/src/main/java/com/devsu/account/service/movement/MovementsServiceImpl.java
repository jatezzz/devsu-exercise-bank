package com.devsu.account.service.movement;

import com.devsu.account.clients.IClientsService;
import com.devsu.account.dto.account.ClientDto;
import com.devsu.account.dto.movement.MovementCreateRequest;
import com.devsu.account.dto.movement.MovementDTO;
import com.devsu.account.exeption.ResourceNotFoundException;
import com.devsu.account.exeption.WrongMovementException;
import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import com.devsu.account.model.MovementType;
import com.devsu.account.service.BaseService;
import com.devsu.account.service.account.AccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class MovementsServiceImpl extends BaseService<MovementDTO, Movement> implements IMovementsService {

    private final IClientsService clientsService;
    private final MovementRepository movementsRepo;
    private final MovementsMapper mapper;
    private final AccountRepository accountRepository;

    public MovementsServiceImpl(IClientsService clientsService, MovementRepository movementsRepo, MovementsMapper mapper, AccountRepository accountRepository) {
        super(movementsRepo, mapper);
        this.clientsService = clientsService;
        this.movementsRepo = movementsRepo;
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    @Override
    public MovementDTO createOne(MovementCreateRequest movementDTO) {
        Movement entity = mapper.toEntity(mapper.toDTO(movementDTO));
        entity.setAccount(Account.builder()
                .number(movementDTO.accountNumber())
                .build());
        Movement movement = getMovement(entity);
        return Optional.of(movement)
                .map(this.mapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Error Interno"));
    }

    private Movement getMovement(Movement movement) {
        if (movement.getAmount().doubleValue() == 0.0) {
            throw new WrongMovementException("La cantidad de movimiento no debe ser 0");
        }
        if (movement.getAmount().doubleValue() > 0 && MovementType.WITHDRAW.equals(movement.getType())) {
            throw new WrongMovementException("Tipo de movimiento incorrecto. Cambie el tipo de movimiento a DEPOSIT o haga que el monto sea negativo.");
        }
        if (movement.getAmount().doubleValue() < 0 && MovementType.DEPOSIT.equals(movement.getType())) {
            throw new WrongMovementException("Tipo de movimiento incorrecto. Cambia el tipo de movimiento a WITHDRAW o haz que el monto sea positivo.");
        }
        Account account = getValidAccount(movement)
                .orElseThrow(() -> new ResourceNotFoundException("Account with number %s was not found...".formatted(movement.getAccount().getNumber())));
        if (MovementType.WITHDRAW == movement.getType() && account.getBalance().doubleValue() == 0) {
            throw new WrongMovementException("Saldo cero");
        }
        if (MovementType.WITHDRAW == movement.getType() && account.getBalance().doubleValue() < Math.abs(movement.getAmount().doubleValue())) {
            throw new WrongMovementException("Saldo no disponible");
        }
        movement.setAccount(account);
        movement.setInitialBalance(BigDecimal.valueOf(account.getBalance().doubleValue()));
        BigDecimal newBalance = BigDecimal.valueOf(account.getBalance().add(movement.getAmount()).doubleValue());
        account.setBalance(newBalance);
        movement.setAccount(account);
        movement.setCurrentBalance(newBalance);
        movementsRepo.persist(movement);
        movementsRepo.persist(account);
        return movement;
    }

    public Optional<Account> getValidAccount(Movement movement) {
        return accountRepository.findValidAccount(movement.getAccount().getNumber(), movement.getAccount().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovementDTO> findAll(Long clientId, LocalDateTime startDate, LocalDateTime endDate) {
        ClientDto client = this.clientsService.getByClientId(clientId);
        if (Objects.isNull(client))
            throw new ResourceNotFoundException("The client with the id %s does not exists...".formatted(clientId));
        return this.movementsRepo.findAllByAccount_ClientIdAndDateIsBetween(clientId, startDate, endDate)
                .map(this.mapper::toDTO)
                .map(movementDTO -> this.mapper.updateClient(movementDTO, client))
                .toList();
    }
}
