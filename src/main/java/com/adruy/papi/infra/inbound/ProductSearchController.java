package com.adruy.papi.infra.inbound;

import com.adruy.papi.application.ProductQueryService;
import com.adruy.papi.application.ProductsNotFoundException;
import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.domain.documents.Product.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductQueryService productQueryService;

    @GetMapping(produces = APPLICATION_NDJSON_VALUE)
    public ResponseEntity<Flux<Product>> findAllProductsBy(@RequestParam(value="name", required = false) String name,
                                                           @RequestParam(value = "size", required = false) Size size,
                                                           @RequestParam(value = "limit", required = false, defaultValue = "10") Integer limit,
                                                           @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset) {
        return new ResponseEntity<>(productQueryService.findAllProductsBy(name, size, limit, offset)
                .switchIfEmpty(Mono.error(new ProductsNotFoundException())), HttpStatus.OK);
    }
}
