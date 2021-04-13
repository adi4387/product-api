package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductFinderController {

    private final ProductQueryService productQueryService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Flux<List<Product>> findProductsByName(@RequestParam @Valid String name) {
        return productQueryService
                .findProductsByName(name);
    }
}
