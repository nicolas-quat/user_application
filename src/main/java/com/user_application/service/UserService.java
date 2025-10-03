package com.user_application.service;

import com.user_application.entity.Utilisateur;
import com.user_application.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Utilisateur findById(Integer id){
        return userRepository.findById(id).orElse(null);
    }

    public Utilisateur createUser(Utilisateur user){
        return userRepository.save(user);
    }
}
