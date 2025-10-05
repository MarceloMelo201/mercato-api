package com.bytenest.mercato_api.repositories;

import com.bytenest.mercato_api.model.entities.ProductSalesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductSaleRepository extends JpaRepository<ProductSalesModel, UUID> {
}
