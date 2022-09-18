package com.lastcivilization.paymentwriteservice.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @JsonProperty("keycloakId")
    private String keycloakId;
    @JsonProperty("account")
    private Long account;
}
