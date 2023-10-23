package com.devsu.account.service;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface ICrudMapper<DTO, ENTITY> {

    DTO toDTO(ENTITY entity);

    ENTITY toEntity(DTO dto);


    @Mapping(target = "id", ignore = true)
    ENTITY updateEntity(@MappingTarget ENTITY entity, DTO dto);

}
