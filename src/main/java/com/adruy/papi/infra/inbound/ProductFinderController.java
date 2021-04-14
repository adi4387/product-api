package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductFinderController {

    private final ProductQueryService productQueryService;

    @GetMapping(produces = APPLICATION_NDJSON_VALUE)
    public Flux<Product> findAllProductsBy(ServerHttpRequest request) {
        return productQueryService
                        .findAllProductsBy(request);
    }
}
