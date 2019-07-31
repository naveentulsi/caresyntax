package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.dto.PatientDto;
import com.caresyntax.ssa.exception.SsaInvalidDataException;
import com.caresyntax.ssa.model.Gender;
import com.caresyntax.ssa.model.Patient;
import com.caresyntax.ssa.repository.PatientRepository;
import com.caresyntax.ssa.service.IPatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PatientService implements IPatientService<Patient> {

    PatientRepository patientRepository;

    @Autowired
    public void setPatientRepository(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     *  Get All patients
     * @return list of all patients in system
     */
    @Override
    public Optional<List<Patient>> getAllPatients() {
        log.info("START PatientService getAllPatients");
        final Iterable<Patient> patientIterable = this.patientRepository.findAll();

        List<Patient> patientList = new ArrayList<>();
        patientIterable.forEach(patient -> {
            patientList.add(patient);
        });

        log.info(String.format("Total patients fetched %d", patientList.size()));
        log.info("END PatientService getAllPatients");
        return patientList.size() > 0 ? Optional.of(patientList) : Optional.empty();
    }

    /**
     *  save a patient entity
     * @param patient
     * @return
     */
    @Transactional
    @Override
    public Patient addPatient(Patient patient) {
        log.info("START PatientService addPatient");
        return this.patientRepository.save(patient);
    }

    /**
     * Method transforms Dto to db entity model and validates data.
     * @param patientData
     * @return
     * @throws SsaInvalidDataException
     */
    @Override
    public Patient createPatientModel(PatientDto patientData) throws SsaInvalidDataException {
        log.info("START PatientService createPatientModel");
        Patient patient = new Patient();
        patient.setName(patientData.getPatientName());
        patient.setDayOfBirth(patientData.getDayOfBirth());

        if (!StringUtils.isEmpty(patientData.getPatientGender()))
            patient.setGender(Gender.valueOf(patientData.getPatientGender()));

        if(!StringUtils.isEmpty(patientData.getDayOfBirth()))
            patient.setDayOfBirth(patientData.getDayOfBirth());

        if(StringUtils.isEmpty(patientData.getPatientName())){
            log.error("Name value is empty");
            throw new SsaInvalidDataException("Patient name cannot be empty", "Invalid Name");
        }


        patient.setId(null);
        log.info("END PatientService createPatientModel");
        return patient;
    }

    @Override
    public Optional<Patient> findPatient(Long Id){
        return this.patientRepository.findById(Id);
    }

}

