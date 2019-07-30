package com.caresyntax.ssa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="DOCTOR")
@Getter
@Setter
@NoArgsConstructor
public class Doctor implements Serializable {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer Id;

    @Column(name = "NAME")
    private String name;
}
