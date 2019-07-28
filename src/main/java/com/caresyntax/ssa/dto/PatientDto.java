package com.caresyntax.ssa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PatientDto {

    @JsonProperty(required = true)
    String patientName;

    @JsonProperty(defaultValue = "NONE")
    String patientGender;

    @JsonProperty
    String dayOfBirth;
}
