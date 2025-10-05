package com.bytenest.mercato_api.dtos.prodSales;

import com.bytenest.mercato_api.dtos.product.ProductResponseDTO;
import com.bytenest.mercato_api.dtos.sale.SaleResponseDTO;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ProdSaleResponseDTO(

        UUID idProdSales,

        double unitPrice,

        ProductResponseDTO productResponseDTO,

        SaleResponseDTO saleResponseDTO
) {
}
