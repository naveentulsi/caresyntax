package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.repository.DoctorRepository;
import com.caresyntax.ssa.service.IDoctorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class DoctorService implements IDoctorService<Doctor> {

    DoctorRepository doctorRepository;

    @Autowired
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        log.info("START DoctorService getAllDoctors");
        return this.doctorRepository.findAll();
    }
}
