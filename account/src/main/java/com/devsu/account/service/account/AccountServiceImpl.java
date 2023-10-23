package com.devsu.account.service.account;

import com.devsu.account.clients.IClientsService;
import com.devsu.account.dto.account.AccountDTO;
import com.devsu.account.dto.account.ClientDto;
import com.devsu.account.dto.movement.MovementDTO;
import com.devsu.account.exeption.ResourceNotFoundException;
import com.devsu.account.model.Account;
import com.devsu.account.service.BaseService;
import com.devsu.account.service.movement.MovementRepository;
import com.devsu.account.service.movement.MovementsMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
public class AccountServiceImpl extends BaseService<AccountDTO, Account> implements IAccountService {

    private final AccountMapper mapper;
    private final MovementRepository movementsRepo;
    private final MovementsMapper movementsMapper;
    private final IClientsService clientsService;

    public AccountServiceImpl(
            AccountRepository accountsRepo,
            MovementRepository movementsRepo,
            AccountMapper mapper,
            MovementsMapper movementsMapper,
            IClientsService clientsService) {
        super(accountsRepo, mapper);
        this.movementsRepo = movementsRepo;
        this.mapper = mapper;
        this.movementsMapper = movementsMapper;
        this.clientsService = clientsService;
    }

    @Override
    public AccountDTO getOne(Long id) {
        AccountDTO account = super.getOne(id);
        ClientDto client = this.clientsService.getByClientId(account.getClient().id());
        return this.mapper.updateDTO(account, client);
    }

    @Override
    @Transactional
    public AccountDTO createOne(AccountDTO accountDTO) {
        ClientDto client = this.clientsService.getByClientId(accountDTO.getClient().id());
        if (Objects.isNull(client))
            throw new ResourceNotFoundException("The client with the id %s does not exists...".formatted(accountDTO.getClient().id()));
        return super.createOne(accountDTO);
    }

    @Override
    @Transactional
    public List<MovementDTO> getAccountMovement(String accountNumber) {
        return this.movementsRepo.findAllByAccount_Number(accountNumber)
                .map(this.movementsMapper::toDTO)
                .toList();
    }
}
