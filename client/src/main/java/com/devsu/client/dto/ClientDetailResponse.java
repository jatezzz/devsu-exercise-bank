package com.devsu.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ClientDetailResponse(
        @JsonProperty("id")
        Long id,
        @JsonProperty("nombre")
        String name,
        @JsonProperty("genero")
        String gender,
        @JsonProperty("edad")
        String age,
        @JsonProperty("identificacion")
        String nationalId,
        @JsonProperty("direccion")
        String address,
        @JsonProperty("telefono")
        String phone,
        @JsonProperty("estado")
        Boolean status
) {
}
