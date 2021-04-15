package com.adruy.papi.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String code;
    private String message;

    public ErrorResponse(HttpStatus status, String message) {
        this.code = String.valueOf(status.value());
        this.message = message;
    }
}
