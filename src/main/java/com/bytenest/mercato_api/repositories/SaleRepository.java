package com.bytenest.mercato_api.repositories;

import com.bytenest.mercato_api.model.entities.SaleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<SaleModel, Long> {
}
