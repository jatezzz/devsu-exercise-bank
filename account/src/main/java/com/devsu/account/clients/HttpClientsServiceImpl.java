package com.devsu.account.clients;

import com.devsu.account.config.properties.ClientServiceProperties;
import com.devsu.account.dto.account.ClientDetailResponse;
import com.devsu.account.dto.account.ClientDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class HttpClientsServiceImpl implements IClientsService {
    private final ClientServiceProperties properties;
    private final ClientMapper mapper;

    public HttpClientsServiceImpl(ClientServiceProperties properties, ClientMapper mapper) {
        this.properties = properties;
        this.mapper = mapper;
    }

    @Override
    public ClientDto getByClientId(Long clientId) {
        try {
            String concat = properties.getBaseUrl().concat("/%s".formatted(clientId));
            URI url = URI.create(concat);
            RestTemplate template = new RestTemplate();
            ClientDetailResponse forObject = template.getForObject(url, ClientDetailResponse.class);
            return mapper.toDTO(forObject);
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return null;
        }
    }
}
