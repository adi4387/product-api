package com.adruy.papi.infra.inbound.handler;

import com.adruy.papi.application.ProductManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductHandlerFunction {

    private final ProductManagerService productManagerService;
}
