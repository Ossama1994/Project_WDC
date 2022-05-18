package com.youcode.wdcmanager.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter @SuperBuilder
@AllArgsConstructor @NoArgsConstructor
@Entity @ToString
@DynamicUpdate
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true, updatable = false)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Boolean status;

}
