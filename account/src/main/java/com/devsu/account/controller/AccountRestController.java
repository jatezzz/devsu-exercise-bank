package com.devsu.account.controller;

import com.devsu.account.dto.account.AccountCreateRequest;
import com.devsu.account.dto.account.AccountDTO;
import com.devsu.account.dto.account.AccountDetailResponse;
import com.devsu.account.dto.account.AccountItemResponse;
import com.devsu.account.dto.movement.MovementItemResponse;
import com.devsu.account.service.account.AccountMapper;
import com.devsu.account.service.account.IAccountService;
import com.devsu.account.service.movement.MovementsMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("cuentas")
public class AccountRestController {

    private final IAccountService service;
    private final AccountMapper mapper;
    private final MovementsMapper movementsMapper;

    public AccountRestController(IAccountService service, AccountMapper mapper, MovementsMapper movementsMapper) {
        this.service = service;
        this.mapper = mapper;
        this.movementsMapper = movementsMapper;
    }

    @GetMapping()
    public List<AccountItemResponse> getAccounts() {
        return service.findAll()
                .stream()
                .map(mapper::toItemResponse)
                .toList();
    }

    @GetMapping("{id}")
    public AccountDetailResponse getAccountDetail(
            @PathVariable Long id) {
        return mapper.toDetailResponse(this.service.getOne(id));
    }

    @PostMapping()
    public AccountDetailResponse createAccount(
            @RequestBody() AccountCreateRequest data
    ) {
        AccountDTO result = this.service.createOne(mapper.toDTO(data));
        return mapper.toDetailResponse(result);
    }

    @DeleteMapping("{id}")
    public void deleteClient(
            @PathVariable Long id) {
        this.service.deleteOne(id);
    }

    @PutMapping("{id}")
    public void updateAccount(
            @PathVariable Long id,
            @RequestBody AccountCreateRequest data
    ) {
        this.service.updateOne(id, mapper.toDTO(data));
    }

    @GetMapping("{accountNumber}/movements")
    public List<MovementItemResponse> createAccount(
            @PathVariable String accountNumber
    ) {
        return this.service.getAccountMovement(accountNumber)
                .stream()
                .map(this.movementsMapper::toItemResponse)
                .toList();
    }
}
