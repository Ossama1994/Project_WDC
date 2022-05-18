package com.youcode.wdcmanager.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.HashSet;

import static javax.persistence.CascadeType.*;

@Getter @Setter @SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity @ToString
public class Participant extends Person {
    private String domain;
    private String structure;
    @ManyToMany(cascade = {DETACH, MERGE, REFRESH})
    private Collection<Activity> activities = new HashSet<>();
}
