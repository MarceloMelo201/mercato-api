package com.bytenest.mercato_api.dtos.product;

public record ProductUpdateDTO(

        String name,

        String sku,

        String description,

        Integer quantity,

        double price
) {
}
