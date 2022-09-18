package com.lastcivilization.paymentwriteservice.domain.port;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;

public interface UserService {

    UserDto getUser(String keycloakId);
}
