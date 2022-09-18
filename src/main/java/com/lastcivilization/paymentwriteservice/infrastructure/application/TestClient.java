package com.lastcivilization.paymentwriteservice.infrastructure.application;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-read-service")
interface TestClient {

    @GetMapping("/users/{keycloakId}")
    String getUser(@PathVariable String keycloakId);

    @GetMapping("/users/{keycloakId}")
    UserDto getUserV2(@PathVariable String keycloakId);
}
