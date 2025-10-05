package com.bytenest.mercato_api.dtos.customer;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CustomerResponseDTO(
        long idCustomer,

        String name,

        String email,

        String telephone,

        String address,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime registrationDate
) {
}
