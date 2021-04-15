package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

public class TestDataInitializer {

    @Autowired
    ProductReactiveRepository productReactiveRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public void initialize() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:products.json");
        List<Product> products = objectMapper.readValue(resource.getFile(), new TypeReference<>() {
        });
        productReactiveRepository
                .deleteAll()
                .thenMany(Flux.fromIterable(products))
                .flatMap(productReactiveRepository::save)
                .thenMany(productReactiveRepository.findAll())
                .doOnNext((product -> System.out.println("Item Inserted: " + product)))
                .blockLast();
    }

}
