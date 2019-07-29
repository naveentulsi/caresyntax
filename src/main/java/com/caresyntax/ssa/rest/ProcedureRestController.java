package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.service.IProcedureService;
import com.caresyntax.ssa.service.impl.ProcedureService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/proc")
@Log4j2
public class ProcedureRestController {

    IProcedureService procedureService;

    @Autowired
    public void setProcedureService(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "Get all procedures.")
    public SsaSimpleResponse getAllProcedure() {
        log.info("START ProcedureRestController getAllProcedure");
        final List<Procedure> allProcedure = this.procedureService.getAllProcedure();
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage("Unable to fetch procedures");

        if (allProcedure != null && allProcedure.size() > 0) {
            log.info("Adding procedures to response entity");
            ssaSimpleResponse.setMessage("Successfully fetched procedures");
            ssaSimpleResponse.setData(allProcedure);
        }

        log.info("END ProcedureRestController getAllProcedure");
        return ssaSimpleResponse;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "Save new procedure data")
    public ResponseEntity<SsaSimpleResponse> storeProcedure(@RequestBody ProcedureDto procedureDto) {
        log.info("START ProcedureRestController getAllProcedure");
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage("Server unable to store procedures");

        final Procedure procedure = this.procedureService.validateAndSetProcedureData(procedureDto);
        final Procedure sProcedure = this.procedureService.saveProcedure(procedure);

        if (sProcedure.getId() != null) {
            ssaSimpleResponse.setMessage("Procedure has been saved successfully");
        }

        log.info("END ProcedureRestController getAllProcedure");
        return new ResponseEntity<>(ssaSimpleResponse, HttpStatus.CREATED);
    }

}
