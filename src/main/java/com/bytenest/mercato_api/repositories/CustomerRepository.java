package com.bytenest.mercato_api.repositories;

import com.bytenest.mercato_api.model.entities.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    //Busca por email
    Optional<CustomerModel> findByEmail(String email);

    //Verifica se o email já existe no bd
    boolean existsByEmail(String email);

    //Busca por nome ignorando letras minusculas ou maiusculas
    List<CustomerModel> findByNameContainingIgnoreCase(String nome);
}
