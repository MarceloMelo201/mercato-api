package com.bytenest.mercato_api.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record SaleCreateDTO(

        @NotBlank
        double total,

        @NotNull
        long idCustomer
) {
}
