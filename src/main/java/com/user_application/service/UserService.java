package com.user_application.service;

import com.user_application.dto.UserDto;
import com.user_application.entity.Utilisateur;
import com.user_application.mapper.UserMapper;
import com.user_application.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findById(Integer id){
        return userMapper.toDTO(userRepository.findById(id).orElse(null));
    }

    public UserDto createUser(UserDto userDto){
        Utilisateur user = userMapper.toEntity(userDto);
        return userMapper.toDTO(userRepository.save(user));
    }
}
