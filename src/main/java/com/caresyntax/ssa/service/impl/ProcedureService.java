package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.exception.SsaInvalidDataException;
import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.model.Patient;
import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.model.ProcedureStatus;
import com.caresyntax.ssa.repository.ProcedureRepository;
import com.caresyntax.ssa.service.IDoctorService;
import com.caresyntax.ssa.service.IPatientService;
import com.caresyntax.ssa.service.IProcedureService;
import com.caresyntax.ssa.utility.IConstants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Optional;

@Service
@Log4j2
public class ProcedureService implements IProcedureService<Procedure> {


    IPatientService patientService;

    IDoctorService doctorService;

    ProcedureRepository procedureRepository;

    @Autowired
    public void setPatientService(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Autowired
    public void setDoctorService(IDoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @Autowired
    public void setProcedureRepository(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    /**
     * Persist procedure entity
     *
     * @param procedure - procedure instance to be persisted
     * @return saved entity
     */
    @Override
    @Transactional
    public Procedure saveProcedure(Procedure procedure) {
        log.info("START ProcedureService saveProcedure");
        try {
            final Procedure sProcedure = this.procedureRepository.save(procedure);
            return sProcedure;
        } catch (Exception ex) {
            log.warn("Exception occurred while saving procedure.");

            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        }
        return null;
    }

    /**
     * find procedure by procedure Id
     *
     * @param Id - procedure
     * @return procedure instance wrapped in an optional
     */
    @Override
    public Optional<Procedure> findById(Integer Id) {
        log.info("START ProcedureService findById");

        try {
            return this.procedureRepository.findById(Id);

        } catch (Exception ex) {
            log.warn("Unable to fetch procedure.");

            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        }
        log.info("END ProcedureService findById");
        return Optional.empty();
    }

    /**
     * Get all procedures
     *
     * @return list of procedures.
     */
    @Override
    public List<Procedure> getAllProcedure() {
        log.info("START ProcedureService getAllProcedure");
        List<Procedure> procedureList = new ArrayList<>();
        try {
            final Iterable<Procedure> procedureListItr = this.procedureRepository.findAll();
            procedureListItr.forEach(procedure -> {
                procedureList.add(procedure);
            });
        } catch (Exception ex) {
            log.warn("Unable to find procedures");
            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        }
        log.info(String.format("Total procedures fetched %d", procedureList.size()));
        log.info("END ProcedureService getAllProcedure");
        return procedureList;
    }

    /**
     * Validates incoming procedure data for business rule,
     * sets it in database entity model if its valid otherwise throws SsaInvalidDataException.
     *
     * @param procedureDto - received procedure data from request
     * @return validated Procedure entity which can be persisted.
     * @throws SsaInvalidDataException if invalid data in found in procedureDto
     */
    @Override
    public Procedure validateAndSetProcedureData(ProcedureDto procedureDto) throws SsaInvalidDataException {
        log.info("START ProcedureService setProcedureData");
        Procedure procedure = new Procedure();

        //check if patient is valid
        if (procedureDto.getPatientId() != null) {
            final Long patientId = procedureDto.getPatientId();
            final Optional<Patient> patientOptional = this.patientService.findPatient(patientId);

            //check valid patient in system, since it is required field
            if (patientOptional.isPresent()) {
                final Patient patient = patientOptional.get();
                procedure.setPatient(patient);
            } else
                throw new SsaInvalidDataException("Patient is required field", "No Patient");
        } else
            throw new SsaInvalidDataException("Patient is required field", "No Patient");

        //check if doctor is valid
        if (procedureDto.getDoctorId() != null) {
            final Integer doctorId = procedureDto.getDoctorId();
            final Optional<Doctor> doctorOptional = this.doctorService.findDoctorById(doctorId);

            //check if doctor is in system
            if (doctorOptional.isPresent()) {
                procedure.setDoctor(doctorOptional.get());
            } else
                throw new SsaInvalidDataException("Doctor is required field", "No Doctor");
        } else
            throw new SsaInvalidDataException("Doctor is required field", "No Doctor");

        //check if description is valid
        if (procedureDto.getDescription() != null && !"".equals(procedureDto.getDescription())) {
            procedure.setDescription(procedureDto.getDescription());
        } else
            throw new SsaInvalidDataException("Description is mandatory field.", "Description");

        //check if plan date in valid
        if (procedureDto.getPlannedStartDate() != null) {
            procedure.setPlannedStartTime(convertStringToDate(procedureDto.getPlannedStartDate()));
        } else
            throw new SsaInvalidDataException("Procedure start date is mandatory.", "Plan Start Date");

        //check if status in valid
        if (procedureDto.getStatus() != null) {
            final ProcedureStatus procedureStatus = ProcedureStatus.valueOf(procedureDto.getStatus());
            procedure.setStatus(procedureStatus);
        } else
            throw new SsaInvalidDataException("Status is required field", "Status");


        //check if plan date in valid
        if (procedureDto.getPlannedEndDate() != null) {
            procedure.setPlannedEndTime(convertStringToDate(procedureDto.getPlannedEndDate()));
        }

        log.info("END ProcedureService setProcedureData");
        return procedure;
    }

    /**
     * Convert date string to date instance in the generic format specified at <code>IConstants.DATE_FORMAT</code>
     *
     * @param dateString string to be converted as Date instance
     * @return parsed date instance
     */
    private Date convertStringToDate(String dateString) {
        log.info("START ProcedureService covertStringToDate");

        Date date = null;
        try {
            date = new SimpleDateFormat(IConstants.DATE_FORMAT).parse(dateString);

        } catch (ParseException ps) {
            log.warn("Unable to parse date string.");
            log.info("Date string " + dateString);
        }
        log.info("END ProcedureService covertStringToDate");
        return date;
    }
}
