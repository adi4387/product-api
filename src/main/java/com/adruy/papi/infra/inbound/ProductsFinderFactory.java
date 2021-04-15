package com.adruy.papi.infra.inbound;

import com.adruy.papi.infra.outbound.repository.ProductReactiveRepository;

import java.util.Map;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

class ProductsFinderFactory {

    private static final String REQUEST_PARAM_NAME = "name";
    private static final String REQUEST_PARAM_SIZE = "size";
    public static final String UNKNOWN_SEARCH_PARAMETER = "Unknown Search Parameter";

    static ProductsFinder productsFinder(Map<String, String> requestParams, ProductReactiveRepository productReactiveRepository) {
        if (requestParams.isEmpty()) {
            return new ProductsGenericFinder(productReactiveRepository);
        } else if (requestParams.containsKey(REQUEST_PARAM_NAME)) {
            return new ProductsFinderNameParam(requestParams.get(REQUEST_PARAM_NAME), productReactiveRepository);
        } else if (requestParams.containsKey(REQUEST_PARAM_SIZE)) {
            return new ProductsFinderSizeParam(requestParams.get(REQUEST_PARAM_SIZE), productReactiveRepository);
        } else {
            throw new IllegalArgumentException(UNKNOWN_SEARCH_PARAMETER);
        }
    }
}
