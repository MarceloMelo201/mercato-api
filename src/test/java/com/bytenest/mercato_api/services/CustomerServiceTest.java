package com.bytenest.mercato_api.services;

import com.bytenest.mercato_api.dtos.customer.CustomerCreateDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.bytenest.mercato_api.mappers.CustomerMapper;
import com.bytenest.mercato_api.model.entities.CustomerModel;
import com.bytenest.mercato_api.repositories.CustomerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerService customerService;

    @Nested
    class createCustomer {

        @Test
        @DisplayName("Deve criar um cliente com sucesso.")
        void deveCriarUmClienteComSucesso() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "Maria Souza",
                    "12345678901",
                    "maria.souza@example.com",
                    "71999999999",
                    "Rua das Flores, 123",
                    LocalDate.of(1998, 5, 10)
            );

            CustomerModel clienteEntity = getNovoCliente(dto);

            CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                    clienteEntity.getIdCustumer(),
                    clienteEntity.getName(),
                    clienteEntity.getEmail(),
                    clienteEntity.getTelephone(),
                    clienteEntity.getAddress(),
                    clienteEntity.getRegistrationDate()

            );


            when(customerMapper.toEntity(dto)).thenReturn(clienteEntity);
            when(customerRepository.save(clienteEntity)).thenReturn(clienteEntity);
            when(customerMapper.toResponse(clienteEntity)).thenReturn(responseDTO);

            CustomerResponseDTO resultado = customerService.saveCustomer(dto);

            assertEquals(clienteEntity.getEmail(), resultado.email());
            assertEquals(clienteEntity.getName(), resultado.name());
            assertEquals(clienteEntity.getTelephone(), resultado.telephone());
            assertEquals(clienteEntity.getAddress(), resultado.address());
            assertEquals(clienteEntity.getRegistrationDate(), resultado.registrationDate());

            verify(customerRepository, times(1)).save(any(CustomerModel.class));

        }

        private static CustomerModel getNovoCliente(CustomerCreateDTO dto) {
            return CustomerModel.builder()
                    .idCustumer(1)
                    .name(dto.name())
                    .cpf(dto.cpf())
                    .email(dto.email())
                    .telephone(dto.telephone())
                    .dateOfBirth(dto.dateOfBirth())
                    .registrationDate(LocalDateTime.now())
                    .address(dto.address())
                    .build();
        }

    }
}