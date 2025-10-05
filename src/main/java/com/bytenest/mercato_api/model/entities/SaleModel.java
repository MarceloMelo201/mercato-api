package com.bytenest.mercato_api.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "sale")
@Table(name = "sales")
public class SaleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idSale;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfSale;

    @Column(nullable = false)
    private double total;

    @ManyToOne(fetch = FetchType.EAGER) //Carrega o cliente sempre que carrega a venda
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;


}
