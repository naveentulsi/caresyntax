package com.caresyntax.ssa.service;


import com.caresyntax.ssa.model.Doctor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IDoctorService<T> {
    List<T> getAllDoctors();

    Optional<Doctor> getDoctorById(Integer Id);

    @Transactional
    Doctor saveDoctor(Doctor doctor);
}
