package com.bytenest.mercato_api.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {
    public ProductAlreadyExistsException() {
        super("Produto já cadastrado no sistema.");
    }
}
