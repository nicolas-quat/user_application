package com.user_application.mapper;

import com.user_application.dto.UserDto;
import com.user_application.entity.Utilisateur;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDTO(Utilisateur user) {
        if (user == null) return null;
        return new UserDto(user.getId(), user.getName(), user.getBirthdate(),
                user.getCountry(), user.getPhone(), user.getGender());
    }

    public Utilisateur toEntity(UserDto userDto) {
        if (userDto == null) return null;
        return new Utilisateur(userDto.getId(), userDto.getName(), userDto.getBirthdate(),
                userDto.getCountry(), userDto.getPhone(), userDto.getGender());
    }
}