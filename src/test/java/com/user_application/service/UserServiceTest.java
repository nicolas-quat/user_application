package com.user_application.service;

import com.user_application.dto.UserDto;
import com.user_application.entity.Utilisateur;
import com.user_application.mapper.UserMapper;
import com.user_application.repository.UserRepository;
import com.user_application.utils.Gender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldSaveUser() {
        UserDto dto = new UserDto();
        dto.setId(1);
        dto.setName("Name");
        dto.setPhone("0658741585");
        dto.setGender(Gender.MALE);
        dto.setCountry("France");
        dto.setBirthdate(new Date(1015252765000L));

        Utilisateur savedUser = new Utilisateur();
        savedUser.setId(1);
        savedUser.setName(dto.getName());
        savedUser.setPhone(dto.getPhone());
        savedUser.setGender(dto.getGender());
        savedUser.setCountry(dto.getCountry());
        savedUser.setBirthdate(dto.getBirthdate());

        when(userMapper.toEntity(dto)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(dto);
        when(userRepository.save(any())).thenReturn(savedUser);

        UserDto result = userService.createUser(dto);

        assertEquals("Name", result.getName());
        assertEquals("0658741585", result.getPhone());
        assertEquals(Gender.MALE, result.getGender());
        assertEquals("France", result.getCountry());
        assertEquals(new Date(1015252765000L), result.getBirthdate());
    }

    @Test
    void shouldThrowExceptionIfNotFrenchAndMinor() {
        UserDto dtoNotFrench = new UserDto();
        dtoNotFrench.setId(1);
        dtoNotFrench.setName("Name");
        dtoNotFrench.setPhone("0658741585");
        dtoNotFrench.setGender(Gender.MALE);
        dtoNotFrench.setCountry("Chinese");
        dtoNotFrench.setBirthdate(new Date(1015252765000L));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(dtoNotFrench));

        UserDto dtoMinor = new UserDto();
        dtoMinor.setId(1);
        dtoMinor.setName("Name");
        dtoMinor.setPhone("0658741585");
        dtoMinor.setGender(Gender.MALE);
        dtoMinor.setCountry("France");
        dtoMinor.setBirthdate(new Date(1444134590000L)); //2015

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(dtoMinor));
    }

}
