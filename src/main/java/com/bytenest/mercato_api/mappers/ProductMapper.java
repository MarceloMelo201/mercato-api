package com.bytenest.mercato_api.mappers;

import com.bytenest.mercato_api.dtos.product.ProductCreateDTO;
import com.bytenest.mercato_api.dtos.product.ProductResponseDTO;
import com.bytenest.mercato_api.model.entities.ProductModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductMapper {

    //Transforma em entidade
    public ProductModel toEntity(ProductCreateDTO dto){
        return ProductModel
                .builder()
                .name(dto.name())
                .sku(dto.sku())
                .price(dto.price())
                .description(dto.description())
                .prodRegisDate(LocalDateTime.now())
                .quantity(dto.quantity())
                .build();
    }

    //Transforma em DTO de resposta
    public ProductResponseDTO toResponse(ProductModel product){
        return ProductResponseDTO
                .builder()
                .idProduct(product.getIdProduct())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .prodRegisDate(product.getProdRegisDate())
                .quantity(product.getQuantity())
                .build();
    }
}
