package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.domain.documents.Product.Size;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductReactiveRepository productReactiveRepository;

    public Flux<Product> findAllProductsBy(String name,
                                           Size size,
                                           Integer limit,
                                           Integer offset) {

        ProductsFinder productsFinder = ProductsFinderFactory.productsFinder(productReactiveRepository, name, size, limit, offset);

        return productsFinder.findProducts()
                .orElseThrow(() -> {
                    throw new ProductsNotFoundException();
                });
    }
}
