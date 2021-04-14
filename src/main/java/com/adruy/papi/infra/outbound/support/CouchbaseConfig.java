package com.adruy.papi.infra.outbound.support;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

@Configuration
@EnableConfigurationProperties({CouchbaseProperties.class})
@RequiredArgsConstructor
@EnableCouchbaseAuditing
@EnableReactiveCouchbaseRepositories(basePackages = {"com.adruy.papi.outbound.repository"})
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    private final CouchbaseProperties couchbaseProperties;

    @Override
    public String getConnectionString() {
        return couchbaseProperties.connectionString();
    }

    @Override
    public String getUserName() {
        return couchbaseProperties.userName();
    }

    @Override
    public String getPassword() {
        return couchbaseProperties.password();
    }

    @Override
    public String getBucketName() {
        return couchbaseProperties.bucketName();
    }
}
