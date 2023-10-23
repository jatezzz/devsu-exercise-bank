package com.devsu.account.service.account;

import com.devsu.account.dto.account.AccountDTO;
import com.devsu.account.dto.movement.MovementDTO;
import com.devsu.account.service.IService;

import java.util.List;

public interface IAccountService extends IService<AccountDTO> {

    List<MovementDTO> getAccountMovement(String accountNumber);
}
