package com.lastcivilization.paymentwriteservice.infrastructure.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "user-read-service", url = "${test.url}")
interface UserClient {

    @GetMapping("/users/{keycloakId}")
    User getUser(@PathVariable String keycloakId);
}
