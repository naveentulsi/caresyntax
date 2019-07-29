package com.caresyntax.ssa.model;

public enum ProcedureStatus {
    PLANNED("PLANNED"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISHED("FINISHED");

    private String procedureStatus;

    private ProcedureStatus(String procedureStatus){
        this.procedureStatus = procedureStatus;
    }

    @Override
    public String toString() {
        return this.procedureStatus;
    }

}
