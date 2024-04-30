package com.user.usermanagerdemo.service;

import com.user.usermanagerdemo.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    List<UserDto> allUsers();
    UserDto getUser(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    UserDto deleteUser(Long id);
}
