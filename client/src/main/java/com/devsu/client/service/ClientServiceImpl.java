package com.devsu.client.service;

import com.devsu.client.dto.ClientDetailResponse;
import com.devsu.client.dto.ClientDto;
import com.devsu.client.dto.ClientDtoRequest;
import com.devsu.client.exception.ResourceNotFoundException;
import com.devsu.client.model.Client;
import com.devsu.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    private final String INFO_URL = "api/cliente";

    private final ClientRepository repository;
    private final ClientMapper mapper;

    @Autowired
    public ClientServiceImpl(ClientRepository repository, ClientMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDetailResponse findById(Long id) {
        return mapper.toDetailResponse(mapper.toDTO(getOptionalEntity(id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientDetailResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toItemResponse)
                .toList();
    }

    @Override
    @Transactional()
    public ResponseEntity<Object> create(ClientDtoRequest request) {
        ClientDto result = createOne(mapper.toDTO(request));
        ClientDetailResponse clientDtoResponse = mapper.toDetailResponse(result);
        return new ResponseEntity<>(clientDtoResponse, HttpStatus.CREATED);

    }

    public ClientDto createOne(ClientDto dto) {
        Client entity = mapper.toEntity(dto);
        Client result = this.repository.save(entity);
        return mapper.toDTO(result);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> delete(Long id) {
        Client entity = getOptionalEntity(id);
        this.repository.delete(entity);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> put(ClientDtoRequest updatedClient, Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(updateOne(id, mapper.toDTO(updatedClient)));
    }

    public ClientDto updateOne(Long id, ClientDto dto) {
        Client entity = getOptionalEntity(id);
        Client save = this.repository.save(mapper.updateEntity(entity, dto));
        return mapper.toDTO(save);
    }

    private Client getOptionalEntity(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, "The client with the id %s was not found..."));
    }

    @Override
    public ResponseEntity<Object> update(ClientDtoRequest updatedClient, Long id) throws ResourceNotFoundException {
        Client client = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, "No se encuentra el cliente"));
        if (updatedClient.name() != null) {
            client.setName(updatedClient.name());
        }
        if (updatedClient.gender() != null) {
            client.setGender(updatedClient.gender());
        }
        if (updatedClient.age() != null) {
            client.setAge(updatedClient.age());
        }
        if (updatedClient.nationalId() != null) {
            client.setNationalId(updatedClient.nationalId());
        }
        if (updatedClient.address() != null) {
            client.setAddress(updatedClient.address());
        }
        if (updatedClient.phone() != null) {
            client.setPhone(updatedClient.phone());
        }
        if (updatedClient.password() != null) {
            client.setPassword(updatedClient.password());
        }
        if (updatedClient.status() != null) {
            client.setStatus(updatedClient.status());
        }
        Client save = repository.save(client);
        return ResponseEntity.ok(mapper.toDTO(save));
    }

    public ClientDto getOne(Long id) {
        return this.repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
