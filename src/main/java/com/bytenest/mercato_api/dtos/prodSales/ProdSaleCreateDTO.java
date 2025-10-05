package com.bytenest.mercato_api.dtos.prodSales;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ProdSaleCreateDTO(

        @NotNull
        double unitPrice,

        @NotNull
        UUID idProduct,

        @NotNull
        long idSale

) {
}
