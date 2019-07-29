package com.caresyntax.ssa.model;

import com.caresyntax.ssa.utility.IConstants;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PROCEDURE")
@Getter
@Setter
@NoArgsConstructor
public class Procedure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PATIENT_FK", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DOCTOR_FK", nullable = false)
    private Doctor doctor;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private ProcedureStatus status;

    @Column(name = "PLANNED_START_TIME", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date plannedStartTime;

    @Column(name = "PLANNED_END_TIME")
    @Temporal(TemporalType.DATE)
    private Date plannedEndTime;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @DateTimeFormat(pattern = IConstants.DATE_FORMAT)
    public Date getPlannedStartTime() {
        return plannedStartTime;
    }

    @DateTimeFormat(pattern = IConstants.DATE_FORMAT)
    public Date getPlannedEndTime() {
        return plannedEndTime;
    }
}
