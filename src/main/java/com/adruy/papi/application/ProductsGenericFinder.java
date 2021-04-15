package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import io.vavr.control.Option;
import lombok.Value;
import reactor.core.publisher.Flux;

@Value
class ProductsGenericFinder implements ProductsFinder {

    ProductReactiveRepository productReactiveRepository;

    @Override
    public Option<Flux<Product>> findProducts() {
        return Option.of(productReactiveRepository.findAll());
    }
}
