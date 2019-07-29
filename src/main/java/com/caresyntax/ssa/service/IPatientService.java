package com.caresyntax.ssa.service;

import com.caresyntax.ssa.dto.PatientDto;
import com.caresyntax.ssa.model.Patient;

import java.util.List;
import java.util.Optional;

public interface IPatientService<T> {
    Optional<List<T>> getAllPatients();

    T addPatient(T patient);

    Patient createPatientModel(PatientDto patientData);

    Optional<Patient> findPatient(Long Id);
}
