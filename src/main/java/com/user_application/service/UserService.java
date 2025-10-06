package com.user_application.service;

import com.user_application.dto.UserDto;
import com.user_application.entity.Utilisateur;
import com.user_application.mapper.UserMapper;
import com.user_application.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto findById(Integer id){
        Utilisateur user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id [" + id + "] not found."));
        return userMapper.toDTO(user);
    }

    public UserDto createUser(UserDto userDto){
        boolean isOver18 = new Date().getTime() - userDto.getBirthdate().getTime() >= 18L * 365 * 24 * 60 * 60 * 1000;
        if (userDto.getCountry().equals("France") && isOver18) {
            Utilisateur user = userMapper.toEntity(userDto);
            return userMapper.toDTO(userRepository.save(user));
        } else {
            throw new IllegalArgumentException("Only adult French residents are allowed to create an account.");
        }
    }
}
