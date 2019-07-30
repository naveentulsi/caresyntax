package com.caresyntax.ssa.utility;

public interface IConstants {
    String PATIENT_NOT_FETCHED = "No patient data";
    String DOCTOR_NOT_FETCHED = "No doctors has been fetched";
    String DOCTOR_FETCHED = "Successfully fetched doctors";
    String PATIENT_FETCHED = "Successfully fetched patients";
    String UNABLE_ADD_PATIENT = "Server unable to add patient";
    String PATIENT_ADDED = "Patient added successfully";
    String DEFAULT_SCHEMA = "caresyntax";
    String DATE_FORMAT = "yyyy/MM/dd";
    String PROCEDURE_NOT_UPDATED = "Server unable to update procedures";
    String PROCEDURE_EMPTY_STATUS = "Server unable to update procedures, cannot update empty status";
    String PROCEDURE_INVALID_ID = "Server unable to update procedures, unable to find procedure with provided Id";
    String PROCEDURE_NO_ID = "Server unable to update procedures, since procedure Id is empty";
    String PROCEDURE_UPDATED = "Status updated successfully";
    String PROCEDURE_FETCH_FAILED = "Unable to fetch procedures";
    String PROCEDURE_FETCH_SUCCESS = "Successfully fetched procedures";
    String PROCEDURE_NOT_SAVE = "Server unable to store procedures";
    String DOCTOR_NOT_SAVED = "Unable to save doctor now.";
    String PROCEDURE_SAVED = "Procedure has been saved successfully";

}
