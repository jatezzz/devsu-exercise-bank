package com.devsu.account.repository;

import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;

public interface CustomMovementsRepo {

    void persist(Movement movement);

    void persist(Account account);
}
