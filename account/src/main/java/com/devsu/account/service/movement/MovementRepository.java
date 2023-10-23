package com.devsu.account.service.movement;

import com.devsu.account.model.Movement;
import com.devsu.account.repository.AbstractEntityRepo;
import com.devsu.account.repository.CustomMovementsRepo;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface MovementRepository extends AbstractEntityRepo<Movement>, CustomMovementsRepo {

    Stream<Movement> findAllByAccount_Number(String accountNumber);

    Stream<Movement> findAllByAccount_ClientIdAndDateIsBetween(Long clientId, LocalDateTime startDate, LocalDateTime endDate);

}
