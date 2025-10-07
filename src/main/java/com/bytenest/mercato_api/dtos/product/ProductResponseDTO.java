package com.bytenest.mercato_api.dtos.product;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record ProductResponseDTO(

        UUID idProduct,

        String name,

        String description,

        BigDecimal price,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime prodRegisDate,

        Integer quantity
) {
}
