package com.bytenest.mercato_api.dtos.product;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductUpdateDTO(

        String name,

        String sku,

        String description,

        @Positive
        Integer quantity,

        @Positive
        BigDecimal price
) {
}
