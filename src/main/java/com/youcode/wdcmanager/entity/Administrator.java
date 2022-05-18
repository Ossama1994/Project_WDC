package com.youcode.wdcmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Getter @Setter @SuperBuilder
@AllArgsConstructor
@Entity @ToString(callSuper = true)
public class Administrator extends Person {
}
