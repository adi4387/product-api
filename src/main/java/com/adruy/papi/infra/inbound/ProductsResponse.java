package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.documents.Product;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ProductsResponse {

    List<ProductDTO> items;

    public ProductsResponse(List<Product> products) {
        items = products.stream()
                .map(product -> new ProductDTO(product.id(), product.name(), product.size()))
                .collect(Collectors.toList());
    }
}
