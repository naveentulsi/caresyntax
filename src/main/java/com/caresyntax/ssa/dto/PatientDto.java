package com.caresyntax.ssa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PatientDto implements Serializable {

    @JsonProperty(required = true)
    String patientName;

    @JsonProperty(defaultValue = "NONE")
    String patientGender;

    @JsonProperty
    String dayOfBirth;
}
