package com.lastcivilization.paymentwriteservice.infrastructure.application.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
record User(
        @JsonProperty("account")
        Long account
){

}
