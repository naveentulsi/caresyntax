package com.caresyntax.ssa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="PATIENT")
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "NAME")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "SEX")
    private Gender gender;

    @Column(name = "DAY_OF_BIRTH")
    private String dayOfBirth;

}
