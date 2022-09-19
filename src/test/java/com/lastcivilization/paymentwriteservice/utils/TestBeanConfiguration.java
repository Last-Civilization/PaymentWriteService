package com.lastcivilization.paymentwriteservice.utils;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import com.lastcivilization.paymentwriteservice.domain.port.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
class TestBeanConfiguration {

    @Bean
    UserService userService(){
        return new UserService() {
            @Override
            public UserDto getUser(String keycloakId) {
                return new UserDto(0L);
            }
        };
    }

}