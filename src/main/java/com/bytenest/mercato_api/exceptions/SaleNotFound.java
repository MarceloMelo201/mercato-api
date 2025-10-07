package com.bytenest.mercato_api.exceptions;

public class SaleNotFound extends RuntimeException {
    public SaleNotFound() {
        super("Venda não encontrada.");
    }
}
