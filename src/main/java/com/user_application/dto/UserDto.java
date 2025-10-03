package com.user_application.dto;

import com.user_application.utils.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String name;
    private Date birthDate;
    private String country;
    private String phone;
    private Gender gender;
}
