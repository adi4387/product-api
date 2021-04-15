package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Value
public class ProductsFinderNameParam implements ProductsFinder {

    ProductReactiveRepository productReactiveRepository;
    String searchValue;
    Integer limit;
    Integer offset;

    public Optional<Flux<Product>> findProducts() {
        return Optional.of(searchValue)
                .map(name -> productReactiveRepository.findByName(name, limit, offset));
    }
}
