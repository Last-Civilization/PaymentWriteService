package com.lastcivilization.paymentwriteservice.infrastructure.service;

import com.lastcivilization.paymentwriteservice.domain.port.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
