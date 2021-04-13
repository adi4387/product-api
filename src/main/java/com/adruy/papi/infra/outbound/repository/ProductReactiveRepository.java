package com.adruy.papi.infra.outbound.repository;

import com.adruy.papi.domain.Product;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface ProductReactiveRepository extends ReactiveCouchbaseRepository<Product, String> {

    Flux<List<Product>> findByName(String name);
}
