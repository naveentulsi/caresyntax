package com.caresyntax.ssa.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcedureDto {

    private String status;
    private Integer doctorId;

    private Long patientId;
    private String description;

    private String plannedEndDate;
    private String plannedStartDate;
}
