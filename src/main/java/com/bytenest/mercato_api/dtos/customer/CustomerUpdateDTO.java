package com.bytenest.mercato_api.dtos.customer;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CustomerUpdateDTO(
        String name,

        String email,

        String telephone,

        String address,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate dateOfBirth
) {
}
