package com.lastcivilization.paymentwriteservice.infrastructure.application;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import liquibase.pro.packaged.S;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class TestContoller {

    private final TestClient testClient;

    @GetMapping("/test/{keycloakId}")
    String test(@PathVariable String keycloakId){
        return testClient.getUser(keycloakId);
    }

    @GetMapping("/test/v2/{keycloakId}")
    UserDto testv2(@PathVariable String keycloakId){
        return testClient.getUserV2(keycloakId);
    }
}
