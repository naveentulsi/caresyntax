package com.caresyntax.ssa.service;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.exception.SsaInvalidDataException;
import com.caresyntax.ssa.model.Procedure;

import java.util.List;
import java.util.Optional;

public interface IProcedureService<T> {
    Procedure saveProcedure(T procedure);

    Optional<Procedure> findById(Integer Id);

    List<T> getAllProcedure();

    Procedure validateAndSetProcedureData(ProcedureDto procedureDto) throws SsaInvalidDataException;
}
