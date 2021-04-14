package com.adruy.papi.infra.inbound;

import com.adruy.papi.domain.Product.Size;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;

@NoArgsConstructor
class ProductSearchRequestParam {

    protected ServerHttpRequest request;

    ProductSearchRequestParam(ServerHttpRequest request) {
        this.request = request;
    }

    protected static String extractValue(ServerHttpRequest request, String requestParamName) {
        return request.getQueryParams().getFirst(requestParamName);
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    static class NameSearchRequestParam extends ProductSearchRequestParam {

        public static final String requestParamName = "name";

        NameSearchRequestParam(ServerHttpRequest request) {
            super(request);
        }

        String value() {
            return extractValue(request, requestParamName);
        }
    }

    @Value
    @EqualsAndHashCode(callSuper = false)
    static class SizeSearchRequestParam extends ProductSearchRequestParam {

        public static final String requestParamName = "size";

        SizeSearchRequestParam(ServerHttpRequest request) {
            super(request);
        }

        String value() {
            return extractValue(request, requestParamName);
        }
    }
}
