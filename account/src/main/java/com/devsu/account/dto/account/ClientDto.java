package com.devsu.account.dto.account;

import com.devsu.account.model.Gender;
import lombok.Builder;

@Builder
public record ClientDto(
        Long id,
        String name,
        Gender gender,
        Integer age,
        String nationalId,
        String address,
        String phone,
        Boolean status
) {
}
