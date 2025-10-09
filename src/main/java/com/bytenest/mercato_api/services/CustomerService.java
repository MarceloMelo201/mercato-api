package com.bytenest.mercato_api.services;

import com.bytenest.mercato_api.dtos.customer.CustomerCreateDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerResponseDTO;
import com.bytenest.mercato_api.dtos.customer.CustomerUpdateDTO;
import com.bytenest.mercato_api.exceptions.CustomerNotFound;
import com.bytenest.mercato_api.exceptions.EmailAlreadyExistsException;
import com.bytenest.mercato_api.mappers.CustomerMapper;
import com.bytenest.mercato_api.model.entities.CustomerModel;
import com.bytenest.mercato_api.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerResponseDTO saveCustomer(CustomerCreateDTO dto) {
        if (customerRepository.existsByEmail(dto.email())) throw new EmailAlreadyExistsException();

        CustomerModel customer = customerRepository.save(customerMapper.toEntity(dto));
        return customerMapper.toResponse(customer);
    }

    public List<CustomerResponseDTO> findAll() {
        return customerRepository.findAll()
                .stream().map(customerMapper::toResponse).toList();
    }

    public List<CustomerResponseDTO> findByName(String name) {
        return customerRepository.findByNameContainingIgnoreCase(name)
                .stream().map(customerMapper::toResponse).toList();
    }

    public CustomerResponseDTO findById(Long id) {
        return customerRepository.findById(id)
                .stream().map(customerMapper::toResponse).findAny()
                .orElseThrow(CustomerNotFound::new);
    }

    public CustomerResponseDTO findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .stream().map(customerMapper::toResponse).findAny()
                .orElseThrow(CustomerNotFound::new);
    }

    @Transactional
    public CustomerResponseDTO updateCustomer(CustomerUpdateDTO dto, Long id) {

        CustomerModel customer = customerRepository
                .findById(id).orElseThrow(CustomerNotFound::new);

        if (customerRepository.existsByEmail(dto.email()) && !Objects.equals(dto.email(), customer.getEmail()))
            throw new EmailAlreadyExistsException();

        if (dto.name() != null) customer.setName(dto.name());
        if (dto.telephone() != null) customer.setTelephone(dto.telephone());
        if (dto.address() != null) customer.setAddress(dto.address());
        if (dto.email() != null) customer.setEmail(dto.email());
        if (dto.dateOfBirth() != null) customer.setDateOfBirth(dto.dateOfBirth());

        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Transactional
    public void deleteCustomer(Long id) {
        CustomerModel customer = customerRepository
                .findById(id).orElseThrow(CustomerNotFound::new);
        customerRepository.delete(customer);
    }

}
