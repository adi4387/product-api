package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface ProductsFinder {

    Optional<Flux<Product>> findProducts();
}
