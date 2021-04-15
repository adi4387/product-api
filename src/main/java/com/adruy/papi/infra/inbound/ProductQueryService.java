package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductReactiveRepository productReactiveRepository;

    public Flux<Product> findAllProductsBy(Map<String, String> requestParams) {

        ProductsFinder productsFinder = ProductsFinderFactory.productsFinder(requestParams, productReactiveRepository);

        return productsFinder.findProducts()
                .getOrElseThrow(() -> {
                    throw new ProductsNotFoundException();
                });
    }
}
