package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.repository.DoctorRepository;
import com.caresyntax.ssa.service.IDoctorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    /**
     * Get doctor by Id
     *
     * @param Id doctor id
     * @return doctor wrapped in a optional
     */
    @Override
    public Optional<Doctor> getDoctorById(Integer Id) {
        log.info("START DoctorService getDoctorById");
        return this.doctorRepository.findById(Id);
    }

    /**
     * Persists doctor entity
     *
     * @param doctor doctor instance to be persisted
     * @return persisted instance
     */
    @Transactional
    @Override
    public Doctor saveDoctor(Doctor doctor) {
        log.info("START DoctorService saveDoctor");
        return this.doctorRepository.save(doctor);
    }
}
