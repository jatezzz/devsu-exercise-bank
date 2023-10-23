package com.devsu.account.service.movement;

import com.devsu.account.dto.account.ClientDto;
import com.devsu.account.dto.movement.*;
import com.devsu.account.model.Movement;
import com.devsu.account.service.ICrudMapper;
import org.mapstruct.*;

import java.time.LocalDateTime;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class}
)
public interface MovementsMapper extends ICrudMapper<MovementDTO, Movement> {

    @Override
    @Mapping(target = "account", source = "account")
    MovementDTO toDTO(Movement movement);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", defaultExpression = "java(LocalDateTime.now())")
    @Mapping(target = "status", defaultValue = "true")
    Movement toEntity(MovementDTO movementDTO);

    @Mapping(target = "accountNumber", source = "account.number")
    MovementItemResponse toItemResponse(MovementDTO movementDTO);

    @Mapping(target = "account", source = "account")
    MovementDetailResponse toDetailResponse(MovementDTO one);

    MovementDTO toDTO(MovementCreateRequest data);

    MovementDTO toDTO(MovementUpdateRequest data);

    @Mapping(source = "client", target = "account.client")
    MovementDTO updateClient(@MappingTarget MovementDTO movement, ClientDto client);

    @Mapping(target = "accountNumber", source = "account.number")
    @Mapping(target = "accountType", source = "account.type")
    @Mapping(target = "movementAmount", source = "amount")
    @Mapping(target = "movementType", source = "type")
    MovementReportItemResponse toReportItemResponse(MovementDTO movementDTO);
}
