package com.youcode.wdcmanager.dto.person;

import com.youcode.wdcmanager.entity.PersonGender;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class GetPersonDto implements Serializable {
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
