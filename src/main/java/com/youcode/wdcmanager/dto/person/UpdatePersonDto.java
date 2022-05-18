package com.youcode.wdcmanager.dto.person;

import com.youcode.wdcmanager.entity.PersonGender;

import java.time.LocalDate;

public class UpdatePersonDto {
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Boolean status;
    private PersonGender gender;
    private LocalDate birthday;
    private String roleName;
}
