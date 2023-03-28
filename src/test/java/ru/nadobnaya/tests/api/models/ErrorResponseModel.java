package ru.nadobnaya.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class ErrorResponseModel {
    private ErrorData error;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public @Data
    static class ErrorData {
        private String message;
    }

}
