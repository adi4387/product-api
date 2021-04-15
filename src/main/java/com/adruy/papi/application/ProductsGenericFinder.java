package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Value
class ProductsGenericFinder implements ProductsFinder {

    ProductReactiveRepository productReactiveRepository;
    Integer limit;
    Integer offset;

    @Override
    public Optional<Flux<Product>> findProducts() {
        return Optional.of(productReactiveRepository.findAll(limit, offset));
    }
}
