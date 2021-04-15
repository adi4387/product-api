package com.adruy.papi.domain.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;
import org.springframework.data.couchbase.core.mapping.id.IdPrefix;

import java.time.LocalDate;

import static org.springframework.data.couchbase.core.mapping.id.GenerationStrategy.USE_ATTRIBUTES;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private static final String DOC_TYPE = "product";

    @Id
    @GeneratedValue(strategy = USE_ATTRIBUTES)
    private String id;

    @IdPrefix
    @Builder.Default
    private String type = DOC_TYPE;

    @IdAttribute
    private String name;

    @IdAttribute(order = 1)
    private Size size;

    @CreatedDate
    private LocalDate creationDate;

    @Version
    private long version;

    public enum Size {
        S1, S2, S3
    }
}
