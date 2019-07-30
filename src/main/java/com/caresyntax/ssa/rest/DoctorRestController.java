package com.caresyntax.ssa.rest;


import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.service.IDoctorService;
import com.caresyntax.ssa.utility.IConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/doc")
@Log4j2
public class DoctorRestController {

    IDoctorService doctorService;

    @Autowired
    public void setDoctorService(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping(name = "Get all doctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public SsaSimpleResponse getAllDoctor() {
        log.info("START DoctorRestController getAllDoctor");
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage(IConstants.DOCTOR_NOT_FETCHED);

        final List<Doctor> doctorList = this.doctorService.getAllDoctors();
        if (doctorList != null && doctorList.size() > 0) {
            log.info(String.format("Total doctors fetched %d", doctorList.size()));
            ssaSimpleResponse.setData(doctorList);
            ssaSimpleResponse.setMessage(IConstants.DOCTOR_FETCHED);
        }

        log.info("END DoctorRestController getAllDoctor");
        return ssaSimpleResponse;
    }

    @PostMapping(name = "Store doctor data to system", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SsaSimpleResponse> addDoctor(@RequestBody Doctor doctor) {
        log.info("START DoctorRestController addDoctor");
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage(IConstants.DOCTOR_NOT_SAVED);

        final Doctor sDoctor = this.doctorService.saveDoctor(doctor);
        if (sDoctor.getId() != null) {
            ssaSimpleResponse.setMessage("Doctor saved.");
            ssaSimpleResponse.setData(sDoctor);
        }

        log.info("END DoctorRestController addDoctor");
        return ResponseEntity.ok(ssaSimpleResponse);
    }

}
