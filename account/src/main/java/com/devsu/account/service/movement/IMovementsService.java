package com.devsu.account.service.movement;

import com.devsu.account.dto.movement.MovementCreateRequest;
import com.devsu.account.dto.movement.MovementDTO;
import com.devsu.account.service.IService;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovementsService extends IService<MovementDTO> {
    List<MovementDTO> findAll(Long clientId, LocalDateTime startDate, LocalDateTime endDate);

    MovementDTO createOne(MovementCreateRequest movementDTO);
}
