package com.caresyntax.ssa.rest;


import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.service.IDoctorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ssaSimpleResponse.setMessage("No doctors has been fetched");

        final List<Doctor> doctorList = this.doctorService.getAllDoctors();
        if (doctorList != null && doctorList.size() > 0) {
            log.info(String.format("Total doctors fetched %d", doctorList.size()));
            ssaSimpleResponse.setData(doctorList);
        }

        log.info("END DoctorRestController getAllDoctor");
        return ssaSimpleResponse;
    }
}
