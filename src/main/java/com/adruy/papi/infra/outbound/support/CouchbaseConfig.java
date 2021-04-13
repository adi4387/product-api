package com.adruy.papi.infra.outbound.support;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.auditing.EnableCouchbaseAuditing;

@Configuration
@EnableConfigurationProperties({CouchbaseProperties.class})
@RequiredArgsConstructor
@EnableCouchbaseAuditing
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
