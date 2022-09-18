package com.lastcivilization.paymentwriteservice.infrastructure.application.service;

import com.lastcivilization.paymentwriteservice.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
}
