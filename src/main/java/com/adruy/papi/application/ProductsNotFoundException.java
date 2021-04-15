package com.adruy.papi.application;

public class ProductsNotFoundException extends RuntimeException {

    public ProductsNotFoundException() {
        super("Products not found");
    }
}
