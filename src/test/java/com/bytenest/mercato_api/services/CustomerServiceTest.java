package com.bytenest.mercato_api.services;

import com.bytenest.mercato_api.dtos.customer.CustomerCreateDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerUpdateDTO;
import com.bytenest.mercato_api.exceptions.CustomerNotFound;
import com.bytenest.mercato_api.exceptions.EmailAlreadyExistsException;
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
import java.util.Optional;

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
    class CreateCustomer {

        @Test
        @DisplayName("Should create a customer successfully")
        void shouldCreateCustomerSuccessfully() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "Maria Souza",
                    "12345678901",
                    "maria.souza@example.com",
                    "71999999999",
                    "Rua das Flores, 123",
                    LocalDate.of(1998, 5, 10)
            );

            CustomerModel clienteEntity = getNovoCliente(dto);

            CustomerResponseDTO responseDTO = getResponseDTO(clienteEntity);

            when(customerMapper.toEntity(dto)).thenReturn(clienteEntity);
            when(customerRepository.save(clienteEntity)).thenReturn(clienteEntity);
            when(customerMapper.toResponse(clienteEntity)).thenReturn(responseDTO);

            CustomerResponseDTO resultado = customerService.saveCustomer(dto);

            assertAll("Verifies customer fields",
                    () -> assertEquals(clienteEntity.getEmail(), resultado.email()),
                    () -> assertEquals(clienteEntity.getName(), resultado.name()),
                    () -> assertEquals(clienteEntity.getTelephone(), resultado.telephone()),
                    () -> assertEquals(clienteEntity.getAddress(), resultado.address()),
                    () -> assertEquals(clienteEntity.getRegistrationDate(), resultado.registrationDate())
            );

            verify(customerRepository, times(1)).save(any(CustomerModel.class));
        }

        @Test
        @DisplayName("Should throw exception for duplicate email")
        void shouldThrowExceptionForDuplicateEmail() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "Maria Souza",
                    "12345678901",
                    "maria.souza@example.com",
                    "71999999999",
                    "Rua das Flores, 123",
                    LocalDate.of(1998, 5, 10)
            );

            when(customerRepository.existsByEmail(dto.email())).thenReturn(true);

            EmailAlreadyExistsException exception = assertThrows(
                    EmailAlreadyExistsException.class,
                    () -> customerService.saveCustomer(dto)
            );

            assertEquals("Email já cadastrado.", exception.getMessage());
            verify(customerRepository, never()).save(any(CustomerModel.class));
        }
    }

    @Nested
    class FindCustomerById {

        @Test
        @DisplayName("Should find customer by id successfully")
        void shouldFindCustomerByIdSuccessfully() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "João Silva",
                    "98765432100",
                    "joao.silva@example.com",
                    "71988887777",
                    "Av. Sete de Setembro, 45",
                    LocalDate.of(1995, 3, 22)
            );
            CustomerModel clienteEntity = getNovoCliente(dto);

            CustomerResponseDTO responseDTO = getResponseDTO(clienteEntity);

            when(customerRepository.findById(clienteEntity.getIdCustumer())).thenReturn(Optional.of(clienteEntity));
            when(customerMapper.toResponse(clienteEntity)).thenReturn(responseDTO);

            CustomerResponseDTO resultado = customerService.findById(clienteEntity.getIdCustumer());

            assertAll("Verifies customer fields",
                    () -> assertEquals(clienteEntity.getEmail(), resultado.email()),
                    () -> assertEquals(clienteEntity.getName(), resultado.name()),
                    () -> assertEquals(clienteEntity.getTelephone(), resultado.telephone()),
                    () -> assertEquals(clienteEntity.getAddress(), resultado.address()),
                    () -> assertEquals(clienteEntity.getRegistrationDate(), resultado.registrationDate())
            );

            verify(customerRepository, times(1)).findById(clienteEntity.getIdCustumer());
        }

        @Test
        @DisplayName("Should throw exception when customer not found by id")
        void shouldThrowExceptionForCustomerNotFound() {

            when(customerRepository.findById(2L)).thenReturn(Optional.empty());

            CustomerNotFound exception = assertThrows(
                    CustomerNotFound.class,
                    () -> customerService.findById(2L)
            );

            assertEquals("Cliente não encontrado", exception.getMessage());
            verify(customerRepository, times(1)).findById(2L);
        }
    }

    @Nested
    class FindCustomerByEmail {

        @Test
        @DisplayName("Should find customer by email successfully")
        void shouldFindCustomerByEmailSuccessfully() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "João Silva",
                    "98765432100",
                    "joao.silva@example.com",
                    "71988887777",
                    "Av. Sete de Setembro, 45",
                    LocalDate.of(1995, 3, 22)
            );

            CustomerModel clienteEntity = getNovoCliente(dto);

            CustomerResponseDTO responseDTO = getResponseDTO(clienteEntity);

            when(customerRepository.findByEmail(clienteEntity.getEmail())).thenReturn(Optional.of(clienteEntity));
            when(customerMapper.toResponse(clienteEntity)).thenReturn(responseDTO);

            CustomerResponseDTO resultado = customerService.findByEmail(clienteEntity.getEmail());

            assertAll("Verifies customer fields",
                    () -> assertEquals(clienteEntity.getEmail(), resultado.email()),
                    () -> assertEquals(clienteEntity.getName(), resultado.name()),
                    () -> assertEquals(clienteEntity.getTelephone(), resultado.telephone()),
                    () -> assertEquals(clienteEntity.getAddress(), resultado.address()),
                    () -> assertEquals(clienteEntity.getRegistrationDate(), resultado.registrationDate())
            );

            verify(customerRepository, times(1)).findByEmail(clienteEntity.getEmail());
        }

        @Test
        @DisplayName("Should throw exception when customer not found by email")
        void shouldThrowExceptionForCustomerNotFound() {

            when(customerRepository.findByEmail("emaildeteste@teste.com.br")).thenReturn(Optional.empty());

            CustomerNotFound exception = assertThrows(
                    CustomerNotFound.class,
                    () -> customerService.findByEmail("emaildeteste@teste.com.br")
            );

            assertEquals("Cliente não encontrado", exception.getMessage());
            verify(customerRepository, times(1)).findByEmail("emaildeteste@teste.com.br");
        }
    }

    @Nested
    class UpdateCustomer {
        @Test
        @DisplayName("Should update customer by id successfully.")
        void shouldUpdateCustomerByIdSuccessfully() {
            CustomerCreateDTO dto = new CustomerCreateDTO(
                    "João Silva",
                    "98765432100",
                    "joao.silva@example.com",
                    "71988887777",
                    "Av. Sete de Setembro, 45",
                    LocalDate.of(1995, 3, 22)
            );

            CustomerUpdateDTO updateDto = new CustomerUpdateDTO(
                    "Carlos Alberto",
                    "carlos.alberto@example.com",
                    "71977776666",
                    "Rua Nova, 123",
                    LocalDate.of(1990, 8, 15)
            );

            CustomerModel clienteEntity = getNovoCliente(dto);

            clienteEntity.setName(updateDto.name());
            clienteEntity.setEmail(updateDto.email());
            clienteEntity.setTelephone(updateDto.telephone());
            clienteEntity.setAddress(updateDto.address());
            clienteEntity.setDateOfBirth(updateDto.dateOfBirth());

            CustomerResponseDTO responseDTO = getResponseDTO(clienteEntity);

            when(customerRepository.findById(clienteEntity.getIdCustumer())).thenReturn(Optional.of(clienteEntity));
            when(customerRepository.save(clienteEntity)).thenReturn(clienteEntity);
            when(customerMapper.toResponse(clienteEntity)).thenReturn(responseDTO);

            CustomerResponseDTO resultado = customerService.updateCustomer(updateDto, 1L);

            assertAll("Verifies updated customer fields",
                    () -> assertEquals(updateDto.email(), resultado.email()),
                    () -> assertEquals(updateDto.name(), resultado.name()),
                    () -> assertEquals(updateDto.telephone(), resultado.telephone()),
                    () -> assertEquals(updateDto.address(), resultado.address()),
                    () -> assertEquals(clienteEntity.getRegistrationDate(), resultado.registrationDate())
            );

            verify(customerRepository, times(1)).save(any(CustomerModel.class));
        }

        @Test
        @DisplayName("Should throw exception when customer not found by id")
        void shouldThrowExceptionForCustomerNotFound() {
            CustomerUpdateDTO updateDto = new CustomerUpdateDTO(
                    "Carlos Alberto",
                    "carlos.alberto@example.com",
                    "71977776666",
                    "Rua Nova, 123",
                    LocalDate.of(1990, 8, 15)
            );

            when(customerRepository.findById(1L)).thenReturn(Optional.empty());

            CustomerNotFound exception = assertThrows(
                    CustomerNotFound.class,
                    () -> customerService.updateCustomer(updateDto, 1L)
            );

            assertEquals("Cliente não encontrado", exception.getMessage());
            verify(customerRepository, times(1)).findById(1L);
        }
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

    private static CustomerResponseDTO getResponseDTO(CustomerModel clienteEntity) {
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(
                clienteEntity.getIdCustumer(),
                clienteEntity.getName(),
                clienteEntity.getEmail(),
                clienteEntity.getTelephone(),
                clienteEntity.getAddress(),
                clienteEntity.getRegistrationDate()
        );
        return responseDTO;
    }

}
