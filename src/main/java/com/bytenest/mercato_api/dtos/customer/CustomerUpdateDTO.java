package com.bytenest.mercato_api.dtos.customer;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;

public record CustomerUpdateDTO(
        String name,

        @Email
        String email,

        String telephone,

        String address,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateOfBirth
) {
}
