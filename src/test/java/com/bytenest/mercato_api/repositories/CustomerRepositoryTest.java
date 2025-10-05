package com.bytenest.mercato_api.repositories;

import com.bytenest.mercato_api.dtos.customer.CustomerCreateDTO;
import com.bytenest.mercato_api.model.entities.CustomerModel;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    @DisplayName("Sucesso ao achar cliente pelo email")
    void findByEmail() {
        CustomerModel customer = createCustomer(new CustomerCreateDTO("teste", "08000000000",
                "testedecliente@customer.com", "719938838383", "rua do lago, 301", LocalDate.of(2006,7,11)));

        saveCustomer(customer);
        Optional<CustomerModel> customerFound = customerRepository.findByEmail(customer.getEmail());

        assertTrue(customerFound.isPresent());
    }

    @Test
    @DisplayName("Sucesso ao verificar cliente pelo email")
    void existsByEmail() {
        CustomerModel customer = createCustomer(new CustomerCreateDTO("teste", "080312000000",
                "testecliente123@customer.com", "719938423383", "rua do tubo, 3505",
                LocalDate.of(2002,5,16)));

        saveCustomer(customer);
        assertTrue(customerRepository.existsByEmail(customer.getEmail()));
    }

    @Test
    @DisplayName("Sucesso ao achar clientes pelo nome")
    void findByNameContainingIgnoreCase() {
        CustomerModel customer = createCustomer(new CustomerCreateDTO
                ("teste", "0812321230",
                "testecliente1@customer.com", "713423423383", "rua do passaro, 3648",
                LocalDate.of(2009,6,26)));

        CustomerModel customer2 = createCustomer(new CustomerCreateDTO
                ("teste123", "08123264731230",
                        "testecliente2@customer.com", "71354423383", "rua 1, 0448",
                        LocalDate.of(2006,10,21)));

        CustomerModel customer3 = createCustomer(new CustomerCreateDTO
                ("test321", "081239789230",
                        "testecliente3@customer.com", "713423423383", "rua 2, 2318",
                        LocalDate.of(2004,4,16)));

        saveCustomer(customer);
        saveCustomer(customer2);
        saveCustomer(customer3);

        List<CustomerModel> customersFound = customerRepository.findByNameContainingIgnoreCase("tes");
        System.out.println(customersFound);
        assertFalse(customersFound.isEmpty());

    }

    private CustomerModel createCustomer(CustomerCreateDTO dto){
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

    private void saveCustomer(CustomerModel customer){
        entityManager.persist(customer);
    }
}