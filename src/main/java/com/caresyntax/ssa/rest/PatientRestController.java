package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.PatientDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.exception.SsaInvalidDataException;
import com.caresyntax.ssa.model.Patient;
import com.caresyntax.ssa.service.IPatientService;
import com.caresyntax.ssa.utility.IConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/patient")
@Log4j2
public class PatientRestController {

    IPatientService patientService;

    @Autowired
    public void setPatientService(IPatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllPatients() {
        log.info("START PatientRestController getAllPatients");

        Optional optionalPatientList;
        SsaSimpleResponse patientListSimpleResponse = new SsaSimpleResponse();
        patientListSimpleResponse.setMessage(IConstants.PATIENT_NOT_FETCHED);
        patientListSimpleResponse.setData(Collections.EMPTY_LIST);

        try {
            optionalPatientList = this.patientService.getAllPatients();

            if (optionalPatientList.isPresent()) {
                List<Patient> patientList = (List<Patient>) optionalPatientList.get();
                patientListSimpleResponse.setData(patientList);
                patientListSimpleResponse.setMessage(IConstants.PATIENT_FETCHED);
            }
        } catch (Exception ex) {
            log.warn("Unable to get patient data");
        }

        log.info("END PatientRestController getAllPatients");
        return ResponseEntity.ok(patientListSimpleResponse);
    }

    /**
     * Add's patient in system, also validates patient data.
     *
     * @param patientData
     * @return
     * @throws SsaInvalidDataException
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addPatient(@RequestBody PatientDto patientData) throws SsaInvalidDataException {
        log.info("START PatientRestController addPatient");
        SsaSimpleResponse addPatientResponse = new SsaSimpleResponse();
        addPatientResponse.setMessage(IConstants.UNABLE_ADD_PATIENT);

        final Patient patient = this.patientService.createPatientModel(patientData);
        final Patient sPatient = (Patient) this.patientService.addPatient(patient);

        if (sPatient.getId() != null) {
            log.info("Patient saved successfully");
            addPatientResponse.setMessage(IConstants.PATIENT_ADDED);
            addPatientResponse.setData(sPatient);
        }
        log.info("END PatientRestController addPatient");
        return ResponseEntity.ok(addPatientResponse);
    }
}
