package com.adruy.papi.application;

import com.adruy.papi.domain.documents.Product.Size;
import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class ProductsFinderFactory {

    static ProductsFinder productsFinder(ProductReactiveRepository productReactiveRepository,
                                         String name,
                                         Size size,
                                         Integer limit,
                                         Integer offset) {
        if (isNotEmpty(name)) {
            return new ProductsFinderNameParam(productReactiveRepository, name, limit, offset);
        } else if (isNotEmpty(size)) {
            return new ProductsFinderSizeParam(productReactiveRepository, String.valueOf(size), limit, offset);
        } else {
            return new ProductsGenericFinder(productReactiveRepository, limit, offset);
        }
    }
}
