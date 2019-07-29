package com.caresyntax.ssa.service;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.exception.SsaInvalidDataException;
import com.caresyntax.ssa.model.Procedure;

import java.util.List;

public interface IProcedureService<T> {
    Procedure saveProcedure(T procedure);

    List<T> getAllProcedure();

    Procedure validateAndSetProcedureData(ProcedureDto procedureDto) throws SsaInvalidDataException;
}
