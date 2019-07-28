package com.caresyntax.ssa.repository;

import com.caresyntax.ssa.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {

}
