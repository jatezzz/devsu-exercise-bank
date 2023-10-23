package com.devsu.account.service.account;

import com.devsu.account.model.Account;
import com.devsu.account.repository.AbstractEntityRepo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends AbstractEntityRepo<Account> {

    Optional<Account> findByNumber(String number);

    @Query("SELECT a FROM Account a WHERE a.number = :number OR a.id = :id")
    Optional<Account> findValidAccount(@Param("number") String number, @Param("id") Long id);

}
