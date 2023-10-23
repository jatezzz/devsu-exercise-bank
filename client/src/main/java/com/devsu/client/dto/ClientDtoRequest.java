package com.devsu.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientDtoRequest(
        @NotBlank(message = "no puede estar vacio")
        @JsonProperty("nombre")
        String name,
        @NotBlank(message = "no puede estar vacio")
        @JsonProperty("genero")
        String gender,
        @NotBlank(message = "no puede estar vacio")
        @JsonProperty("edad")
        String age,
        @JsonProperty("identificacion")
        @NotBlank(message = "no puede estar vacio")
        String nationalId,
        @JsonProperty("direccion")
        String address,
        @JsonProperty("telefono")
        String phone,
        @NotBlank(message = "no puede estar vacio")
        @Size(min = 2, max = 12, message = "el tamaño debe de estar entre 2 y 12")
        @JsonProperty("contraseña")
        String password,
        @JsonProperty("estado")
        Boolean status
) {
}
