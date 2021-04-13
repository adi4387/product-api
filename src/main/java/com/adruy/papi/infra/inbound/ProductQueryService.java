package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductReactiveRepository productReactiveRepository;

    public Flux<List<Product>> findProductsByName(String name) {
        return productReactiveRepository.findByName(name);
    }
}
