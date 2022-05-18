package com.youcode.wdcmanager.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

import static javax.persistence.CascadeType.*;

@Getter @Setter @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
@DynamicUpdate
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private String description;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private ActivityType type;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean status;
    @ManyToMany(mappedBy = "activities", cascade = {DETACH, MERGE, REFRESH})
    private Collection<Participant> participants = new HashSet<>();
    @OneToOne(mappedBy = "activity", cascade = {DETACH, MERGE, REFRESH})
    private Responsible responsible;
    @OneToOne(cascade = {DETACH, MERGE, REFRESH})
    private Exercise exercise;
}
