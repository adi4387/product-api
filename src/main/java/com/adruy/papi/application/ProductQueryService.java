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

    public Flux<Product> findAllProductsByName(String name,
                                               Integer limit,
                                               Integer offset) {


        return productReactiveRepository.findByName(name, limit, offset);
    }

    public Flux<Product> findAllProductsBySize(String size,
                                               Integer limit,
                                               Integer offset) {
        return productReactiveRepository.findByName(size, limit, offset);

    }

    public Flux<Product> findAllProducts(Integer limit,
                                         Integer offset) {

        return productReactiveRepository.findAll(limit, offset);
    }
}
