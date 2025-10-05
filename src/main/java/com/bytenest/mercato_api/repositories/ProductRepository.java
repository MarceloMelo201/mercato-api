package com.bytenest.mercato_api.repositories;

import com.bytenest.mercato_api.model.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

    //busca por nome ignorando letras minusculas ou maiusculas
    Optional<ProductModel> findByNameContainingIgnoreCase(String name);
}
