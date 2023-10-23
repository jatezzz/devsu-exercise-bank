package com.devsu.client.service;

import com.devsu.client.dto.ClientDetailResponse;
import com.devsu.client.dto.ClientDto;
import com.devsu.client.dto.ClientDtoRequest;
import com.devsu.client.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ClientMapper extends ICrudMapper<ClientDto, Client> {

    ClientDetailResponse toItemResponse(Client clientDto);

    ClientDetailResponse toDetailResponse(ClientDto one);

    ClientDto toDTO(ClientDtoRequest data);
}
