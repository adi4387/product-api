package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import io.vavr.control.Option;
import reactor.core.publisher.Flux;

public interface ProductsFinder {

    Option<Flux<Product>> findProducts();
}
