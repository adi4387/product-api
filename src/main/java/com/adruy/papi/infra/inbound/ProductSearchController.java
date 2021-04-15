package com.adruy.papi.infra.inbound;

import com.adruy.papi.application.ProductQueryService;
import com.adruy.papi.application.ProductsNotFoundException;
import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.domain.documents.Product.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Max;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductSearchController {

    private static final String LIMIT = "limit";
    private static final String OFFSET = "offset";
    private static final String SIZE = "size";
    private static final String NAME = "name";
    private static final String DEFAULT_LIMIT = "10";
    private static final String DEFAULT_OFFSET = "0";
    private final ProductQueryService productQueryService;

    @GetMapping(produces = APPLICATION_NDJSON_VALUE, params = NAME)
    public ResponseEntity<Flux<Product>> findAllProductsByName(@RequestParam(value = NAME) String name,
                                                               @RequestParam(value = LIMIT, required = false, defaultValue = DEFAULT_LIMIT) @Max(50) Integer limit,
                                                               @RequestParam(value = OFFSET, required = false, defaultValue = DEFAULT_OFFSET) Integer offset) {
        return new ResponseEntity<>(productQueryService.findAllProductsBy(name, limit, offset)
                .switchIfEmpty(Mono.error(new ProductsNotFoundException())), HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_NDJSON_VALUE, params = SIZE)
    public ResponseEntity<Flux<Product>> findAllProductsBySize(@RequestParam(value = SIZE) Size size,
                                                               @RequestParam(value = LIMIT, defaultValue = DEFAULT_LIMIT) @Max(50) Integer limit,
                                                               @RequestParam(value = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset) {
        return new ResponseEntity<>(productQueryService.findAllProductsBy(size, limit, offset)
                .switchIfEmpty(Mono.error(new ProductsNotFoundException())), HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_NDJSON_VALUE, params = {LIMIT, OFFSET})
    public ResponseEntity<Flux<Product>> findAllProducts(@RequestParam(value = LIMIT, defaultValue = DEFAULT_LIMIT) @Max(50) Integer limit,
                                                         @RequestParam(value = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset) {
        return new ResponseEntity<>(productQueryService.findAllProducts(limit, offset)
                .switchIfEmpty(Mono.error(new ProductsNotFoundException())), HttpStatus.OK);
    }
}
