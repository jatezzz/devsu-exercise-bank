package com.devsu.client.service;

import org.mapstruct.MappingTarget;

public interface ICrudMapper<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(DTO dto);

    ENTITY updateEntity(@MappingTarget ENTITY entity, DTO dto);

}
