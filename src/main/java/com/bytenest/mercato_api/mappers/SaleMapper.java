package com.bytenest.mercato_api.mappers;

import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.bytenest.mercato_api.dtos.sale.SaleCreateDTO;
import com.bytenest.mercato_api.dtos.sale.SaleResponseDTO;
import com.bytenest.mercato_api.model.entities.CustomerModel;
import com.bytenest.mercato_api.model.entities.SaleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SaleMapper {

    //Transforma em entidade
    public SaleModel toEntity(SaleCreateDTO dto, CustomerModel customer){
        return SaleModel
                .builder()
                .dateOfSale(LocalDateTime.now())
                .total(dto.total())
                .customer(customer)
                .build();
    }


    //Transforma em DTO de resposta
    public SaleResponseDTO toResponse(SaleModel sale, CustomerResponseDTO dto){
        return SaleResponseDTO
                .builder()
                .idSale(sale.getIdSale())
                .total(sale.getTotal())
                .dateOfSale(sale.getDateOfSale())
                .customerResponse(dto)
                .build();
    }
}
