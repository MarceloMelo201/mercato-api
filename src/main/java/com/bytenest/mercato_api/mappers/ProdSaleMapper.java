package com.bytenest.mercato_api.mappers;

import com.bytenest.mercato_api.dtos.prodSales.ProdSaleCreateDTO;
import com.bytenest.mercato_api.dtos.prodSales.ProdSaleResponseDTO;
import com.bytenest.mercato_api.dtos.product.ProductResponseDTO;
import com.bytenest.mercato_api.dtos.sale.SaleResponseDTO;
import com.bytenest.mercato_api.model.entities.ProductModel;
import com.bytenest.mercato_api.model.entities.ProductSalesModel;
import com.bytenest.mercato_api.model.entities.SaleModel;
import org.springframework.stereotype.Component;

@Component
public class ProdSaleMapper {

    //Transforma em entidade
    public ProductSalesModel toEntity(ProdSaleCreateDTO prodSale,
                                      SaleModel sale,
                                      ProductModel product){

        return ProductSalesModel
                .builder()
                .unitPrice(prodSale.unitPrice())
                .sale(sale)
                .product(product)
                .build();
    }


    //Transforma em DTO de resposta
    public ProdSaleResponseDTO toResponse(ProductSalesModel prodSale,
                                          ProductResponseDTO productResponseDTO,
                                          SaleResponseDTO saleResponseDTO){

        return ProdSaleResponseDTO
                .builder()
                .idProdSales(prodSale.getIdProdSales())
                .unitPrice(prodSale.getUnitPrice())
                .saleResponseDTO(saleResponseDTO)
                .productResponseDTO(productResponseDTO)
                .build();
    }


}
