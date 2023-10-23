//package com.devsu.account;
//
//import com.devsu.account.clients.IClientsService;
//import com.devsu.account.dto.movement.MovementCreateRequest;
//import com.devsu.account.dto.movement.MovementDTO;
//import com.devsu.account.model.Account;
//import com.devsu.account.model.AccountType;
//import com.devsu.account.service.account.AccountRepository;
//import com.devsu.account.service.movement.MovementRepository;
//import com.devsu.account.service.movement.MovementsMapper;
//import com.devsu.account.service.movement.MovementsMapperImpl;
//import com.devsu.account.service.movement.MovementsServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import java.math.BigDecimal;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class MovementsServiceImplTest {
//
//    @MockBean
//    private IClientsService clientsService;
//
//    @MockBean
//    private MovementRepository movementsRepo;
//
//    @MockBean
//    private MovementsMapper movementsMapper;
//
//    @MockBean
//    private AccountRepository accountRepository;
//
//    @Test
//    public void createOneTest() {
//        MovementsMapper bMapper = new MovementsMapperImpl();
//        MovementsServiceImpl service = new MovementsServiceImpl(clientsService, movementsRepo, bMapper, accountRepository);
//        // Arrange
//        MovementCreateRequest request = new MovementCreateRequest("478759", BigDecimal.ONE, "DEPOSIT", true);
//        Account account = new Account(1L, "478759", AccountType.SAVINGS, BigDecimal.ONE, true);
//
//        when(accountRepository.findValidAccount(anyString(), anyLong())).thenReturn(Optional.of(account));
//
//        // Act
//        MovementDTO result = service.createOne(request);
//
//        // Assert
//        Assertions.assertNotNull(result);
//    }
//}
