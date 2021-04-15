package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import com.adruy.papi.support.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.adruy.papi.domain.documents.Product.Size.S1;
import static com.adruy.papi.infra.inbound.ProductSearchRequestParam.NameSearchRequestParam.ILLEGAL_PRODUCT_NAME;
import static com.adruy.papi.infra.inbound.ProductSearchRequestParam.SizeSearchRequestParam.INVALID_SIZE_ERROR_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;
import static org.springframework.http.MediaType.APPLICATION_NDJSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient(timeout = "PT10M")
@TestInstance(PER_CLASS)
public class ProductFinderControllerIT extends TestDataInitializer {

    @Autowired
    protected WebTestClient webTestClient;

    @Test
    public void should_return_all_products() {

        webTestClient
                .get()
                .uri("/products")
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(10, (long) response.getResponseBody().size()));
    }

    @Test
    public void should_return_product_when_queried_by_product_name() {

        var productName = "CDRs";

        webTestClient
                .get()
                .uri("/products?name=" + productName)
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(productName, response.getResponseBody().stream().findFirst().get().name()));
    }

    @Test
    public void should_return_product_when_queried_by_product_size() {

        var productSize = S1;

        webTestClient
                .get()
                .uri("/products?size=" + productSize.name())
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class)
                .consumeWith((response) -> assertEquals(productSize, response.getResponseBody().stream().findFirst().get().size()));
    }

    @Test
    public void should_return_400_error_when_queried_by_name_with_illegal_characters() {

        var productName = "@@@";

        webTestClient
                .get()
                .uri("/products?name=" + productName)
                .accept(APPLICATION_NDJSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBodyList(ErrorResponse.class)
                .consumeWith((response) -> assertEquals(ILLEGAL_PRODUCT_NAME, response.getResponseBody().stream().findFirst().get().message()));
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
                .expectBodyList(ErrorResponse.class)
                .consumeWith((response) -> assertEquals(INVALID_SIZE_ERROR_MESSAGE, response.getResponseBody().stream().findFirst().get().message()));
    }
}
