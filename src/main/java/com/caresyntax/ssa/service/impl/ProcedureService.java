package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.repository.ProcedureRepository;
import com.caresyntax.ssa.service.IProcedureService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

@Service
@Log4j2
public class ProcedureService implements IProcedureService<Procedure> {

    ProcedureRepository procedureRepository;

    @Autowired
    public void setProcedureRepository(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    public List<Procedure> getAllProcedure() {
        log.info("START ProcedureService getAllProcedure");
        final Iterable<Procedure> all = this.procedureRepository.findAll();
        List<Procedure> procedureList = new ArrayList<>();
        all.forEach(procedure -> {
            procedureList.add(procedure);
        });
        log.info(String.format("Total procedures fetched %d", procedureList.size()));
        log.info("END ProcedureService getAllProcedure");
        return procedureList;
    }
}
