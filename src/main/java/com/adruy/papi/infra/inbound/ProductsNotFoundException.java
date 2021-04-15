package com.adruy.papi.infra.inbound;

public class ProductsNotFoundException extends RuntimeException {

    public ProductsNotFoundException() {
        super("Products not found");
    }
}
