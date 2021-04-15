package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.Value;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Value
public class ProductsFinderSizeParam implements ProductsFinder {

    public static final String INVALID_SIZE_ERROR_MESSAGE = "Invalid Product Size Passed. It should be either S1, S2, S3";

    ProductReactiveRepository productReactiveRepository;
    String searchValue;
    Integer limit;
    Integer offset;

    @Override
    public Optional<Flux<Product>> findProducts() {
        return Optional.of(searchValue)
                .map(name -> productReactiveRepository.findBySize(name, limit, offset));
    }
}
