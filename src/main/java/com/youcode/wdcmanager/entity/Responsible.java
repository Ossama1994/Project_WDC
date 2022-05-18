package com.youcode.wdcmanager.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import static javax.persistence.CascadeType.*;

@Getter @Setter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity @ToString(callSuper = true)
public class Responsible extends Person {
    private String domain;
    @OneToOne(cascade = {DETACH, PERSIST, MERGE, REFRESH})
    private ResponsibleType type;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private Activity activity;
}
