package com.adruy.papi.infra.inbound;

import com.adruy.papi.application.ProductManagerService;
import com.adruy.papi.domain.documents.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Mono<ProductDTO>> addNewProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productManagerService.registerProduct(product)
                .map(item -> new ProductDTO(product.id(), product.name(), product.size())), HttpStatus.CREATED);
    }
}
