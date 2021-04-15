package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductManagerService {

    private final ProductReactiveRepository productReactiveRepository;

    public Mono<Product> registerProduct(Product product) {
        return productReactiveRepository.save(product);
    }
}
