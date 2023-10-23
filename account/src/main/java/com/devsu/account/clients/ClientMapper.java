package com.devsu.account.clients;

import com.devsu.account.dto.account.ClientDetailResponse;
import com.devsu.account.dto.account.ClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientMapper {

    ClientDto toDTO(ClientDetailResponse data);
}
