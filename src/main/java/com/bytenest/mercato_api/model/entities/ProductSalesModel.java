package com.bytenest.mercato_api.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "product_sale")
@Table(name = "products_sales")
public class ProductSalesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProdSales;

    @Column(nullable = false)
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private SaleModel sale;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private ProductModel product;


}
