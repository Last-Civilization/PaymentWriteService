package com.lastcivilization.paymentwriteservice.domain.dto;

public record UserDto (
        String keycloakId,
        Long account
) {}
