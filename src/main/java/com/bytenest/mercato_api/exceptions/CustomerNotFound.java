package com.bytenest.mercato_api.exceptions;

public class CustomerNotFound extends RuntimeException {
    public CustomerNotFound() {
        super("Cliente não encontrado");
    }
}
