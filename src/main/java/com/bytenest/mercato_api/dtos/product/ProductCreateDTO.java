package com.bytenest.mercato_api.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        String sku,

        @NotBlank
        String description,

        @NotNull
        Integer quantity,

        @NotNull
        double price


) {
}
