package com.devsu.client.dto;

import lombok.Builder;

@Builder
public record ClientDto(
        Long id,
        String name,
        String gender,
        String age,
        String nationalId,
        String address,
        String phone,
        Boolean status
) {
}