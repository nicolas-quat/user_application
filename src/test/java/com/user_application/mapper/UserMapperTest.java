package com.user_application.mapper;

import com.user_application.dto.UserDto;
import com.user_application.entity.User;
import com.user_application.utils.Gender;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void testToDto() {
        User user =  new User(1, "Nicolas", new Date(812733225000L), "France", "0685478547", Gender.MALE);

        UserDto dto = mapper.toDTO(user);

        assertNotNull(dto);
        assertEquals("Nicolas", dto.getName());
        assertEquals(new Date(812733225000L), dto.getBirthdate());
        assertEquals("France", dto.getCountry());
        assertEquals("0685478547", dto.getPhone());
        assertEquals(Gender.MALE, dto.getGender());

        user.setId(1);
    }

    @Test
    void testToEntity() {
        UserDto userDto = new UserDto(1, "Nicolas", new Date(812733225000L), "France", "0685478547", Gender.MALE);

        User user = mapper.toEntity(userDto);

        assertNotNull(user);
        assertEquals("Nicolas", user.getName());
        assertEquals(new Date(812733225000L), user.getBirthdate());
        assertEquals("France", user.getCountry());
        assertEquals("0685478547", user.getPhone());
        assertEquals(Gender.MALE, user.getGender());
    }

    @Test
    void testNullInput() {
        assertNull(mapper.toDTO(null));
        assertNull(mapper.toEntity(null));
    }
}
