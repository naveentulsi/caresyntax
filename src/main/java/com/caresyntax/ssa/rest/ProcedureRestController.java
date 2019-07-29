package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.service.IProcedureService;
import com.caresyntax.ssa.service.impl.ProcedureService;
import com.caresyntax.ssa.utility.IConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//only for local development
//TODO - remove before deployment.
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

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, name = "Update procedure status")
    public ResponseEntity<SsaSimpleResponse> updateProcedure(@RequestBody Procedure procedure) {
        log.info("START ProcedureRestController updateProcedure");
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage("Server unable to update procedures");

        if (procedure.getId() != null) {
            final Optional<Procedure> procedureOptional = this.procedureService.findById(procedure.getId());

            if (procedureOptional.isPresent()) {
                final Procedure sProcedure = procedureOptional.get();
                log.info("Procedure instance fetched");

                if (procedure.getStatus() != null) {
                    sProcedure.setStatus(procedure.getStatus());
                    this.procedureService.saveProcedure(sProcedure);
                    ssaSimpleResponse.setMessage(IConstants.PROCEDURE_UPDATED);
                } else {
                    log.info("No status field");
                    ssaSimpleResponse.setMessage(IConstants.PROCEDURE_EMPTY_STATUS);
                }

            } else {
                ssaSimpleResponse.setMessage(IConstants.PROCEDURE_INVALID_ID);
            }

        } else {
            ssaSimpleResponse.setMessage(IConstants.PROCEDURE_NO_ID);
        }
        log.info("END ProcedureRestController updateProcedure");
        return ResponseEntity.ok(ssaSimpleResponse);
    }

}
