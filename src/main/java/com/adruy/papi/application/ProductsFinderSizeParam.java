package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.Value;
import reactor.core.publisher.Flux;

@Value
public class ProductsFinderSizeParam implements ProductsFinder {

    public static final String INVALID_SIZE_ERROR_MESSAGE = "Invalid Product Size Passed. It should be either S1, S2, S3";

    String searchValue;
    ProductReactiveRepository productReactiveRepository;

    @Override
    public Option<Flux<Product>> findProducts() {
        return Option.of(searchValue)
                .peek(this::validateSize)
                .map(name -> productReactiveRepository.findBySize(name));
    }

    private void validateSize(String size) {
        Try.of(() -> Product.Size.valueOf(size))
                .onFailure(ex -> {
                    throw new IllegalArgumentException(INVALID_SIZE_ERROR_MESSAGE);
                });
    }
}
