package com.bytenest.mercato_api.exceptions;

public class ProductNotFound extends RuntimeException {
    public ProductNotFound() {
        super("Produto não encontrado.");
    }
}
