package com.user.usermanagerdemo.service.serviceImpl;

import com.user.usermanagerdemo.service.UserManagerFileWriter;
import com.user.usermanagerdemo.dto.UserDto;
import com.user.usermanagerdemo.exception.UserManagerException;
import com.user.usermanagerdemo.mapper.UserMapper;
import com.user.usermanagerdemo.repository.UserRepository;
import com.user.usermanagerdemo.model.User;
import com.user.usermanagerdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // make it recognizable as a bean
public class UserServiceImpl implements UserService {

    @Autowired // dependency injection, inject dependency of the repository
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserManagerFileWriter userManagerFileWriter;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toUser(userDto);
        User savedUser = userRepository.save(user);

        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.POST_FILE, savedUser);
        userManagerFileWriter.writeFile(UserManagerFileWriter.POST_FILE, formatedData);

        return userMapper.toUserDto(savedUser);
    }

    @Override
    public List<UserDto> allUsers() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserManagerException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto)
    {
        User user = userRepository.findById(id).orElseThrow(() -> new UserManagerException("User not found", HttpStatus.NOT_FOUND));
        User newUserInfo = userMapper.toUser(userDto);

        user.setName(newUserInfo.getName());
        user.setEmail(newUserInfo.getEmail());
        user.setPhoneNumber(newUserInfo.getPhoneNumber());
        user.setAddress(newUserInfo.getAddress());

        userRepository.save(user);

        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.PUT_FILE, user);
        userManagerFileWriter.writeFile(UserManagerFileWriter.PUT_FILE, formatedData);

        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserManagerException("User not found", HttpStatus.NOT_FOUND));
        UserDto userDto = userMapper.toUserDto(user);
        userRepository.deleteById(id);

        String formatedData = userManagerFileWriter.getFormatedData(UserManagerFileWriter.DELETE_FILE, user);
        userManagerFileWriter.writeFile(UserManagerFileWriter.DELETE_FILE, formatedData);

        return userDto;
    }
}
