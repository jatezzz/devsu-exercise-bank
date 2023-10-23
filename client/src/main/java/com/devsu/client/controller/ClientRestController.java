package com.devsu.client.controller;

import com.devsu.client.dto.ClientDetailResponse;
import com.devsu.client.dto.ClientDtoRequest;
import com.devsu.client.exception.ResourceNotFoundException;
import com.devsu.client.service.IClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClientRestController {

    private final IClientService service;

    @Autowired
    public ClientRestController(IClientService service) {
        this.service = service;
    }

    @GetMapping()
    public List<ClientDetailResponse> getClients() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ClientDetailResponse getClient(@PathVariable Long id) {
        return this.service.findById(id);
    }

    @PostMapping()
    public ResponseEntity<Object> post(@Valid @RequestBody ClientDtoRequest input) {
        return service.create(input);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@Valid
                                 @RequestBody ClientDtoRequest input,
                                 @PathVariable Long id) throws ResourceNotFoundException {
        return service.put(input, id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDtoRequest input,
                                    @PathVariable Long id) throws ResourceNotFoundException {
        return service.update(input, id);
    }

}
