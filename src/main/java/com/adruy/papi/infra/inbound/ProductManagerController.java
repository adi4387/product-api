package com.adruy.papi.infra.inbound;

import com.adruy.papi.application.ProductManagerService;
import com.adruy.papi.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductManagerController {

    private final ProductManagerService productManagerService;

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public Mono<Product> addNewProduct(@RequestBody Product product) {
        return productManagerService
                .registerProduct(product);
    }
}
