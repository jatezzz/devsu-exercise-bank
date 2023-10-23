package com.devsu.account.repository;

import com.devsu.account.model.Account;
import com.devsu.account.model.Movement;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomMovementsRepoImpl implements CustomMovementsRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void persist(Movement movement) {
        entityManager.persist(movement);
    }

    @Override
    @Transactional
    public void persist(Account account) {
        entityManager.persist(account);
    }
}
