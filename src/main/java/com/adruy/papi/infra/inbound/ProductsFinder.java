package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import io.vavr.control.Option;
import reactor.core.publisher.Flux;

interface ProductsFinder {

    Option<Flux<Product>> findProducts();
}
