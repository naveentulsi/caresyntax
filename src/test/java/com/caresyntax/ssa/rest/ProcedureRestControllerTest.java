package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.model.ProcedureStatus;
import com.caresyntax.ssa.service.IProcedureService;
import com.caresyntax.ssa.service.impl.ProcedureService;
import com.caresyntax.ssa.utility.IConstants;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ProcedureRestControllerTest {
    @Mock
    IProcedureService procedureService;
    @Mock
    Logger log;
    @InjectMocks
    ProcedureRestController procedureRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetProcedureService() throws Exception {
        procedureRestController.setProcedureService(new ProcedureService());
    }

    /**
     * Method tests when all procedures are fetched and validates the response message.
     *
     * @throws Exception
     */
    @Test
    public void testGetAllProcedureSuccess() throws Exception {

        final List<Procedure> procedureList = Arrays.<Procedure>asList(new Procedure());
        when(procedureService.getAllProcedure()).thenReturn(procedureList);

        SsaSimpleResponse result = procedureRestController.getAllProcedure();
        Assert.assertEquals(IConstants.PROCEDURE_FETCH_SUCCESS, result.getMessage());
        Assert.assertEquals(procedureList, result.getData());
    }

    /**
     * Method tests when no procedures are fetched and validates negative response message.
     *
     * @throws Exception
     */
    @Test
    public void testGetAllProcedureFailure() throws Exception {

        when(procedureService.getAllProcedure()).thenReturn(null);

        SsaSimpleResponse result = procedureRestController.getAllProcedure();
        Assert.assertEquals(IConstants.PROCEDURE_FETCH_FAILED, result.getMessage());
        Assert.assertEquals(null, result.getData());
    }

    /**
     * Test case to check if procedure saved and validate message. Also the HTTPStatus code should be 201 - CREATED
     *
     * @throws Exception
     */
    @Test
    public void testStoreProcedureSuccess() throws Exception {

        final ProcedureDto procedureDto = prepareProcedureDtoTestData();
        final Procedure procedure = prepareProcedureTestData();
        when(procedureService.saveProcedure(any())).thenReturn(procedure);
        when(procedureService.validateAndSetProcedureData(any())).thenReturn(procedure);

        ResponseEntity<SsaSimpleResponse> result = procedureRestController.storeProcedure(procedureDto);
        Assert.assertEquals(201, result.getStatusCodeValue());
        Assert.assertEquals(IConstants.PROCEDURE_SAVED, result.getBody().getMessage());
    }

    /**
     * Test case to check if procedure is not saved and validate negative message. Also the HTTPStatus code should be 200 - OK
     *
     * @throws Exception
     */
    @Test
    public void testStoreProcedureFailure() throws Exception {

        final ProcedureDto procedureDto = prepareProcedureDtoTestData();
        final Procedure procedure = prepareProcedureTestData();
        procedure.setId(null);

        when(procedureService.saveProcedure(any())).thenReturn(procedure);
        when(procedureService.validateAndSetProcedureData(any())).thenReturn(procedure);

        ResponseEntity<SsaSimpleResponse> result = procedureRestController.storeProcedure(procedureDto);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(IConstants.PROCEDURE_NOT_SAVE, result.getBody().getMessage());
    }

    /**
     * Method testes the positive flow of update - checks response message and status code.
     *
     * @throws Exception
     */
    @Test
    public void testUpdateProcedure() throws Exception {
        // prepare test data with Id - update case
        final Procedure procedure = prepareProcedureTestData();
        procedure.setId(12);

        when(procedureService.saveProcedure(any())).thenReturn(procedure);
        when(procedureService.findById(anyInt())).thenReturn(Optional.of(procedure));

        ResponseEntity<SsaSimpleResponse> result = procedureRestController.updateProcedure(procedure);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(IConstants.PROCEDURE_UPDATED, result.getBody().getMessage());
    }

    /**
     * Test case to check when no procedure status is not sent to the API.
     * @throws Exception
     */
    @Test
    public void testUpdateProcedureWithNoStatusValue() throws Exception {
        // prepare test data with Id and status as null
        final Procedure procedure = prepareProcedureTestData();
        procedure.setId(12);
        procedure.setStatus(null);

        when(procedureService.saveProcedure(any())).thenReturn(procedure);
        when(procedureService.findById(anyInt())).thenReturn(Optional.of(procedure));

        ResponseEntity<SsaSimpleResponse> result = procedureRestController.updateProcedure(procedure);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(IConstants.PROCEDURE_EMPTY_STATUS, result.getBody().getMessage());
    }

    /**
     * prepares test data
     *
     * @return procedure test instance
     */
    private Procedure prepareProcedureTestData() {
        Procedure procedure = new Procedure();
        procedure.setId(1);
        procedure.setDescription("Sample Description");
        procedure.setStatus(ProcedureStatus.valueOf("PLANNED"));

        return procedure;
    }

    /**
     * prepares test data
     *
     * @return procedureDto test instance
     */
    private ProcedureDto prepareProcedureDtoTestData() {
        ProcedureDto procedureDto = new ProcedureDto();
        procedureDto.setStatus("PLANNED");
        procedureDto.setDescription("Sample description");

        procedureDto.setDoctorId(15);
        procedureDto.setPatientId(17L);
        procedureDto.setPlannedEndDate("2019/08/22");

        return procedureDto;
    }
}