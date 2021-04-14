package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static com.adruy.papi.domain.Product.Size.S1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT10M")
public class ProductManagerControllerIT {

    @Autowired
    protected WebTestClient webTestClient;

    @Test
    public void should_return_product_id_when_new_product_is_added() {
        var delimiter = ".";
        var documentName = "product";
        var product = Product.builder().name("Phone").size(S1).build();
        var expectedProductId = documentName.concat(delimiter).concat(product.name()).concat(delimiter).concat(product.size().name());

        webTestClient
                .post()
                .uri("/products")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .body(BodyInserters.fromValue(product))
                .exchange()
                .expectStatus().isCreated()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(expectedProductId, response.getResponseBody().stream().findFirst().get().id()));
    }
}
