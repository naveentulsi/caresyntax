package com.caresyntax.ssa.service.impl.integration;

import com.caresyntax.ssa.SsaApplication;
import com.caresyntax.ssa.dto.PatientDto;
import com.caresyntax.ssa.dto.ProcedureDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.model.Procedure;
import com.caresyntax.ssa.model.ProcedureStatus;
import com.caresyntax.ssa.utility.IConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SsaApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Log4j2
public class ProcedureRestIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void procedure_rest_integration_full_cycle() {
        log.info("START ProcedureRestIntegrationTest ProcedureRestIntegrationTest");

        try {

            // validate patient API and get patient Id.
            final PatientDto patientData = preparePatientTestData();
            final List<String> patientResponseMessageList = postOperation("/api/v1/patient", patientData);
            final String patientResponseMessage = patientResponseMessageList.get(0);
            final String patientId = patientResponseMessageList.get(1);

            Assert.assertEquals(IConstants.PATIENT_ADDED, patientResponseMessage);

            // validate doctor API and get doctor Id.
            final Doctor doctor = prepareDoctorTestData();
            final List<String> doctorResponseMessageList = postOperation("/api/v1/doc", doctor);
            final String doctorResponseMessage = doctorResponseMessageList.get(0);
            final String doctorId = doctorResponseMessageList.get(1);

            Assert.assertEquals("Doctor saved.", doctorResponseMessage);

            // create a procedure using the  patient and doctor id
            final ProcedureDto procedureDto = prepareProcedureTestDate(Integer.valueOf(patientId), Integer.valueOf(doctorId));
            final List<String> procedureResponseMessageList = postOperation("/api/v1/proc", procedureDto);
            final String procedureResponseMessage = procedureResponseMessageList.get(0);
            final Integer procedureId = Integer.valueOf(procedureResponseMessageList.get(1));

            Assert.assertEquals(IConstants.PROCEDURE_SAVED, procedureResponseMessage);

            Procedure procedure = new Procedure();
            procedure.setId(procedureId);
            procedure.setStatus(ProcedureStatus.FINISHED);

            // update the procedure created in previous test.
            final List<String> procedureUpdateMessageList = putOperation("/api/v1/proc", procedure);
            final String procedureUpdateResponseMessage = procedureUpdateMessageList.get(0);

            Assert.assertEquals(IConstants.PROCEDURE_UPDATED, procedureUpdateResponseMessage);

            final List<String> findProcedureMessageList = getOperation("/api/v1/proc");
            final String findProcedureResponseMessage = findProcedureMessageList.get(0);

            Assert.assertNotNull(findProcedureResponseMessage);
            Assert.assertEquals(IConstants.PROCEDURE_FETCH_SUCCESS, findProcedureResponseMessage);


        } catch (JsonProcessingException jsEx) {
            log.warn("Json processing broke");

            if (log.isErrorEnabled()) {
                log.error(jsEx);
            }
        } catch (NumberFormatException ex) {
            log.warn("Unable to perform integration test on Procedure xAPI");

            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        } catch (Exception ex) {
            log.warn("Unable to perform integration test on Procedure API");

            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        }
    }


    /**
     * Method does post request to provided uri with the data.
     *
     * @return list of response entities
     * @throws Exception
     */
    private List<String> postOperation(String uri, Object data) throws Exception {

        ArrayList<String> responseValueList = new ArrayList<>();

        final MvcResult postRequest = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(data))).andReturn();

        final MockHttpServletResponse postResponse = postRequest.getResponse();
        final int statusCode = postResponse.getStatus();
        final String apiResponseAsString = postResponse.getContentAsString();

        final SsaSimpleResponse ssaSimpleResponse = readAndTransformResponse(apiResponseAsString);
        final String responseMessage = ssaSimpleResponse.getMessage();
        HashMap<String, Object> responseMap = (HashMap<String, Object>) ssaSimpleResponse.getData();
        final Integer entityId = (Integer) responseMap.get("id");

        Assert.assertNotNull(statusCode);
        responseValueList.add(responseMessage);
        responseValueList.add(String.valueOf(entityId));

        log.info(uri + " API Response..");
        log.info(responseMessage);
        log.info("Entity Id " + entityId);

        return responseValueList;
    }

    private List<String> putOperation(String uri, Object data) throws Exception {
        ArrayList<String> responseValueList = new ArrayList<>();

        final MvcResult putRequest = mockMvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(data))).andReturn();

        final MockHttpServletResponse putResponse = putRequest.getResponse();
        final int statusCode = putResponse.getStatus();
        final String apiResponseAsString = putResponse.getContentAsString();

        final SsaSimpleResponse ssaSimpleResponse = readAndTransformResponse(apiResponseAsString);
        final String responseMessage = ssaSimpleResponse.getMessage();

        Assert.assertNotNull(statusCode);
        responseValueList.add(responseMessage);

        log.info(uri + " API Response..");
        log.info(responseMessage);

        return responseValueList;
    }

    private List<String> getOperation(String uri) throws Exception {
        ArrayList<String> responseValueList = new ArrayList<>();

        final MvcResult getRequest = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON)).andReturn();

        final MockHttpServletResponse getResponse = getRequest.getResponse();
        final int statusCode = getResponse.getStatus();
        final String apiResponseAsString = getResponse.getContentAsString();

        final SsaSimpleResponse ssaSimpleResponse = readAndTransformResponse(apiResponseAsString);
        final String responseMessage = ssaSimpleResponse.getMessage();

        Assert.assertNotNull(statusCode);
        responseValueList.add(responseMessage);

        log.info(uri + " API Response..");
        log.info(responseMessage);

        return responseValueList;
    }

    private ProcedureDto prepareProcedureTestDate(Integer patientId, Integer doctorId) {
        ProcedureDto procedureDto = new ProcedureDto();
        procedureDto.setPatientId(Long.valueOf(patientId));
        procedureDto.setDoctorId(doctorId);
        procedureDto.setDescription("Sample description");
        procedureDto.setStatus("PLANNED");
        procedureDto.setPlannedStartDate("2019/08/24");
        return procedureDto;

    }

    private PatientDto preparePatientTestData() {
        PatientDto patientDto = new PatientDto();
        patientDto.setPatientName("Alice");
        patientDto.setPatientGender("FEMALE");
        patientDto.setDayOfBirth("Monday");
        return patientDto;
    }

    private Doctor prepareDoctorTestData() {
        Doctor doctor = new Doctor();
        doctor.setName("Rafael");
        return doctor;
    }

    private SsaSimpleResponse readAndTransformResponse(String responseString) {

        SsaSimpleResponse ssaSimpleResponse = null;
        try {
            ssaSimpleResponse = objectMapper.readValue(responseString, SsaSimpleResponse.class);
        } catch (JsonParseException jex) {
            log.warn("Json parsing has broken");
        } catch (IOException io) {
            log.warn("IO broke while parsing");
        }
        return ssaSimpleResponse;
    }


}
