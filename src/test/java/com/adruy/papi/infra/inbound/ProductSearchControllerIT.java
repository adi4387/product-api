package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.support.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.http.MediaType.APPLICATION_NDJSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT10M")
@TestInstance(PER_CLASS)
public class ProductSearchControllerIT extends TestDataInitializer {

    @Autowired
    protected WebTestClient webTestClient;

    @Test
    public void should_return_all_products() {

        webTestClient
                .get()
                .uri("/products?limit=2&offset=2")
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(2, response.getResponseBody().stream().count()));
    }

    @Test
    public void should_return_product_when_queried_by_product_name() {

        var productName = "CDRs";

        webTestClient
                .get()
                .uri("/products?name=" + productName + "&limit=1&offset=1")
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(productName, response.getResponseBody().stream().findFirst().get().name()));
    }

    @Test
    public void should_return_product_when_queried_by_product_size() {

        var productSize = "S1";

        webTestClient
                .get()
                .uri("/products?size=" + productSize + "&limit=2&offset=0")
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(2, response.getResponseBody().stream().count()));
    }

    @Test
    public void should_return_400_error_when_queried_by_invalid_size() {

        var productSize = "S4";

        webTestClient
                .get()
                .uri("/products?size=" + productSize)
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBodyList(ErrorResponse.class);
    }
}
