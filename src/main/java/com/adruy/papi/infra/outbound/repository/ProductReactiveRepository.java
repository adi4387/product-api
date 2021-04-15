package com.adruy.papi.infra.outbound.repository;

import com.adruy.papi.domain.documents.Product;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductReactiveRepository extends ReactiveCouchbaseRepository<Product, String> {

    Flux<Product> findByName(String name);

    Flux<Product> findBySize(String size);
}
