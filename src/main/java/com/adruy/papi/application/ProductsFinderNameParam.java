package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import io.vavr.control.Option;
import lombok.Value;
import reactor.core.publisher.Flux;

@Value
public class ProductsFinderNameParam implements ProductsFinder {

    String searchValue;
    ProductReactiveRepository productReactiveRepository;

    public Option<Flux<Product>> findProducts() {
        return Option.of(searchValue)
                .map(name -> productReactiveRepository.findByName(name));
    }
}
