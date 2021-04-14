package com.adruy.papi.support;

import com.adruy.papi.domain.Product;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

@Component
@Profile("DEV")
@RequiredArgsConstructor
public class InitializeProductBucket implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(InitializeProductBucket.class);

    @Autowired
    private final ProductReactiveRepository productReactiveRepository;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void run(String... args) {
        initializeProductBucket();
    }

    private void initializeProductBucket() {

        try {
            List<Product> products = getProductsFromJsonFile();
            productReactiveRepository
                    .deleteAll()
                    .thenMany(Flux.fromIterable(products))
                    .flatMap(productReactiveRepository::save)
                    .thenMany(productReactiveRepository.findAll())
                    .subscribe(product -> LOG.info("Product Inserted : " + product));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Product> getProductsFromJsonFile() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:products.json");
        return objectMapper.readValue(resource.getFile(), new TypeReference<>() {
        });
    }
}
