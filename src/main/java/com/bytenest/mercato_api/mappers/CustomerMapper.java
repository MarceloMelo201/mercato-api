package com.bytenest.mercato_api.mappers;

import com.bytenest.mercato_api.dtos.customer.CustomerCreateDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.bytenest.mercato_api.model.entities.CustomerModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class CustomerMapper {

    //Transforma em entidade
    public CustomerModel toEntity(CustomerCreateDTO dto){
        return CustomerModel
                .builder()
                .name(dto.name())
                .cpf(dto.cpf())
                .email(dto.email())
                .dateOfBirth(dto.dateOfBirth())
                .registrationDate(LocalDateTime.now())
                .telephone(dto.telephone())
                .address(dto.address())
                .build();
    }


    //Transforma em DTO de resposta
    public CustomerResponseDTO toResponse(CustomerModel customer){
        return CustomerResponseDTO
                .builder()
                .idCustomer(customer.getIdCustumer())
                .name(customer.getName())
                .email(customer.getEmail())
                .telephone(customer.getTelephone())
                .address(customer.getAddress())
                .registrationDate(customer.getRegistrationDate())
                .build();
    }
}
