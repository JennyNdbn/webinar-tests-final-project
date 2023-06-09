package ru.nadobnaya.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class LoginBodyModel {
    String email, password;
    Boolean rememberMe;
}
