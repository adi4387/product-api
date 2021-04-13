package com.adruy.papi.infra.outbound.support;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Value
@ConstructorBinding
@ConfigurationProperties("env.couchbase")
public class CouchbaseProperties {

    String connectionString;
    String userName;
    String password;
    String bucketName;
}
