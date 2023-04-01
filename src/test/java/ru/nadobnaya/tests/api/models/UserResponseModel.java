package ru.nadobnaya.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
public @Data class UserResponseModel {

    private Integer id;
    private String email;
    private String nickname;
    private String name;
    private String defaultEventType;

}
