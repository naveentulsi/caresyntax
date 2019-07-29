package com.caresyntax.ssa.utility;

public interface IConstants {
    String NO_PATIENT_FETCHED = "No patient data";
    String NO_DOCTOR_FETCHED = "No doctors has been fetched";
    String DOCTOR_FETCHED = "Successfully fetched doctors";
    String PATIENT_FETCHED = "Successfully fetched patients";
    String UNABLE_ADD_PATIENT = "Server unable to add patient";
    String PATIENT_ADDED = "Patient added successfully";
    String DEFAULT_SCHEMA = "caresyntax";
    String DATE_FORMAT = "yyyy/MM/dd";
    String PROCEDURE_EMPTY_STATUS = "Server unable to update procedures, cannot update empty status";
    String PROCEDURE_INVALID_ID = "Server unable to update procedures, unable to find procedure with provided Id";
    String PROCEDURE_NO_ID = "Server unable to update procedures, since procedure Id is empty";
    String PROCEDURE_UPDATED = "Status updated successfully";
}
