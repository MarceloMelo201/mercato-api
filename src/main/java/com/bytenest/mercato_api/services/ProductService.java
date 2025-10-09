package com.bytenest.mercato_api.services;


import com.bytenest.mercato_api.dtos.product.ProductCreateDTO;
import com.bytenest.mercato_api.dtos.product.ProductResponseDTO;
import com.bytenest.mercato_api.dtos.product.ProductUpdateDTO;
import com.bytenest.mercato_api.exceptions.ProductAlreadyExistsException;
import com.bytenest.mercato_api.exceptions.ProductNotFound;
import com.bytenest.mercato_api.mappers.ProductMapper;
import com.bytenest.mercato_api.model.entities.ProductModel;
import com.bytenest.mercato_api.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Transactional
    public ProductResponseDTO saveProduct(ProductCreateDTO dto){
        if(productRepository.existsBySku(dto.sku())) throw new ProductAlreadyExistsException();
        ProductModel product = productRepository.save(productMapper.toEntity(dto));
        return productMapper.toResponse(product);
    }

    public List<ProductResponseDTO> findAll(){
        return productRepository.findAll()
                .stream().map(productMapper::toResponse).toList();
    }

    public ProductResponseDTO findById(UUID id){
        return productRepository.findById(id)
                .map(productMapper::toResponse)
                .orElseThrow(ProductNotFound::new);
    }

    @Transactional
    public ProductResponseDTO updateProduct(UUID id, ProductUpdateDTO dto){
        ProductModel product = productRepository.findById(id)
                .orElseThrow(ProductNotFound::new);

        if(dto.sku() != null && productRepository.existsBySku(dto.sku())
                && !dto.sku().equals(product.getSku()))
            throw new ProductAlreadyExistsException();

        if(dto.name() != null) product.setName(dto.name());
        if(dto.sku() != null) product.setSku(dto.sku());
        if(dto.description() != null) product.setDescription(dto.description());
        if(dto.quantity() != null) product.setQuantity(dto.quantity());
        if(dto.price() != null) product.setPrice(dto.price());

        return productMapper.toResponse(productRepository.save(product));
    }

    @Transactional
    public void deleteProdcut(UUID id){
        ProductModel product = productRepository.findById(id)
                .orElseThrow(ProductNotFound::new);
        productRepository.delete(product);
    }
}
