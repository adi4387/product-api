package com.adruy.papi.infra.outbound.repository;

import com.adruy.papi.domain.documents.Product;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductReactiveRepository extends ReactiveCouchbaseRepository<Product, String> {

    @Query("#{#n1ql.selectEntity} where name = $1 LIMIT $2 OFFSET $3")
    Flux<Product> findByName(String name, Integer limit, Integer offset);

    @Query("#{#n1ql.selectEntity} where size = $1 LIMIT $2 OFFSET $3")
    Flux<Product> findBySize(String size, Integer limit, Integer offset);

    @Query("#{#n1ql.selectEntity} LIMIT $1 OFFSET $2")
    Flux<Product> findAll(Integer limit, Integer offset);
}
