package com.lastcivilization.paymentwriteservice.utils;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import com.lastcivilization.paymentwriteservice.domain.exception.UserNotFoundException;
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
                if(keycloakId.equals("1")){
                    return new UserDto(1L);
                }
                if(keycloakId.equals("3")){
                    return new UserDto(3L);
                }
                throw new UserNotFoundException(keycloakId);
            }
        };
    }

}
