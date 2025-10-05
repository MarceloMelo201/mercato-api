package com.bytenest.mercato_api.dtos.customer;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record CustomerCreateDTO(
        @NotBlank
        String name,

        @NotBlank
        String cpf,

        @Email
        String email,

        @NotBlank
        String telephone,

        @NotBlank
        String address,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateOfBirth
) {
}
