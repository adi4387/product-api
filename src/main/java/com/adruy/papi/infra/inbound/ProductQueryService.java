package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Service
@RequiredArgsConstructor
public class ProductQueryService {

    private final ProductReactiveRepository productReactiveRepository;

    public Flux<Product> findAllProductsBy(ServerHttpRequest request) {
        Flux<Product> productFlux;

        final var name = new ProductSearchRequestParam.NameSearchRequestParam(request).value();
        final var size = new ProductSearchRequestParam.SizeSearchRequestParam(request).value();

        if (isNotEmpty(name)) {
            productFlux = productReactiveRepository.findByName(name);
        } else if (isNotEmpty(size)) {
            productFlux = productReactiveRepository.findBySize(size);
        } else {
            productFlux = productReactiveRepository.findAll();
        }
        return productFlux;
    }
}
