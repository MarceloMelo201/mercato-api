package com.bytenest.mercato_api.dtos.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateDTO(

        @NotBlank
        String name,

        @NotBlank
        String sku,

        @NotBlank
        String description,

        @Positive
        Integer quantity,

        @Positive
        BigDecimal price


) {
}
