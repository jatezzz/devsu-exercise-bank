package com.devsu.account.clients;

import com.devsu.account.dto.account.ClientDto;

public interface IClientsService {
    ClientDto getByClientId(Long clientId);
}
