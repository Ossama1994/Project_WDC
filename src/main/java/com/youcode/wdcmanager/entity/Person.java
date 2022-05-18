package com.youcode.wdcmanager.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Getter @Setter @SuperBuilder @AllArgsConstructor @NoArgsConstructor
@Entity @ToString
@Inheritance
@DynamicUpdate
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true)
    private String username;
    private String firstName;
    private String lastName;
    private String phone;
    @Column(unique = true)
    private String email;
    private String password;
    private Boolean status = false;
    private PersonGender gender;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private Role role;
    private Instant loginExpireAt;
}
