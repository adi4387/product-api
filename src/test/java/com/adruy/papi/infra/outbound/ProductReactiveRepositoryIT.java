package com.adruy.papi.infra.outbound;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.util.List;

import static com.adruy.papi.domain.documents.Product.Size.S1;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@SpringBootTest
@TestInstance(PER_CLASS)
public class ProductReactiveRepositoryIT {

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

    @Test
    public void should_return_all_products() {

        // when
        Flux<Product> productFlux = productReactiveRepository.findAll();

        // then
        StepVerifier.create(productReactiveRepository.findAll())
                .expectSubscription()
                .expectNextCount(10)
                .verifyComplete();
    }

    @Test
    public void should_add_product() {
        //given
        var product = Product.builder().name("Orange").size(S1).build();

        // when
        Mono<Product> productMono = productReactiveRepository.save(product);

        // then
        StepVerifier.create(productMono)
                .expectSubscription()
                .expectNext(product)
                .verifyComplete();
    }

    @Test
    public void should_return_products_when_user_searches_by_name() {
        //given
        var productName = "CDRs";

        // when
        Flux<Product> productFlux = productReactiveRepository.findByName(productName);

        // then
        StepVerifier.create(productFlux)
                .expectSubscription()
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    public void should_return_products_when_user_searches_by_size() {
        //given
        var productSize = "S2";

        // when
        Flux<Product> productFlux = productReactiveRepository.findBySize(productSize);

        // then
        StepVerifier.create(productFlux)
                .expectSubscription()
                .expectNextCount(2)
                .verifyComplete();
    }
}
