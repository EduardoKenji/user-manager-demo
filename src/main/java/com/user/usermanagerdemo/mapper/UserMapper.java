package com.user.usermanagerdemo.mapper;

import com.user.usermanagerdemo.dto.UserDto;
import com.user.usermanagerdemo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    List<UserDto> toUserDtos(List<User> users);
}
