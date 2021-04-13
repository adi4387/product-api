package com.adruy.papi.infra.outbound.repository;

import com.adruy.papi.domain.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.couchbase.BucketDefinition;
import org.testcontainers.couchbase.CouchbaseContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static com.adruy.papi.domain.Product.Size.S2;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Testcontainers
public class ProductReactiveRepositoryTest {

//    @Container
//    private CouchbaseContainer couchbaseContainer = new CouchbaseContainer("couchbase:community")
//            .withBucket(new BucketDefinition("test-bucket"));
//
//    @BeforeAll
//    public void setUp() {
//        couchbaseContainer.start();
//    }

    @Autowired
    ProductReactiveRepository productReactiveRepository;

    @Test
    public void should_add_product() {
        productReactiveRepository.save(Product.builder().name("Laptop").size(S2).build());
    }
}
