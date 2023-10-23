package com.devsu.client.service;

import com.devsu.client.dto.ClientDetailResponse;
import com.devsu.client.dto.ClientDto;
import com.devsu.client.dto.ClientDtoRequest;
import com.devsu.client.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IClientService {

    ClientDetailResponse findById(Long id);

    List<ClientDetailResponse> findAll();

    ResponseEntity<Object> create(ClientDtoRequest clientDtoRequest);

    ResponseEntity<Object> put(ClientDtoRequest clientDtoRequest, Long id) throws ResourceNotFoundException;

    ResponseEntity<Object> update(ClientDtoRequest clientDtoRequest, Long id) throws ResourceNotFoundException;

    ResponseEntity<Object> delete(Long id);

    ClientDto getOne(Long id);
}
