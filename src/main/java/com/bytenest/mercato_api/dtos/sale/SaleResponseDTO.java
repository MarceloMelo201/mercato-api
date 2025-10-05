package com.bytenest.mercato_api.dtos.sale;

import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public record SaleResponseDTO(
        long idSale,

        double total,

        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime dateOfSale,

        CustomerResponseDTO customerResponse

) {
}
