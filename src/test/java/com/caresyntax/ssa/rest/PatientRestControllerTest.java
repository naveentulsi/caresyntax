package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.PatientDto;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Gender;
import com.caresyntax.ssa.model.Patient;
import com.caresyntax.ssa.service.IPatientService;
import com.caresyntax.ssa.utility.IConstants;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.swing.text.html.Option;
import java.util.*;

import static org.mockito.Mockito.*;

public class PatientRestControllerTest {
    @Mock
    IPatientService patientService;
    @Mock
    Logger log;
    @InjectMocks
    PatientRestController patientRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPatients() throws Exception {

        final Optional<List<Patient>> optionalPatientList = prepareTestDataForGetAllPatients();

        when(patientService.getAllPatients()).thenReturn(optionalPatientList);

        ResponseEntity result = patientRestController.getAllPatients();
        final HttpStatus statusCode = result.getStatusCode();
        final SsaSimpleResponse ssaSimpleResponse = (SsaSimpleResponse) result.getBody();
        final String responseMessage = ssaSimpleResponse.getMessage();

        Assert.assertNotNull(responseMessage);
        //check actual request status
        Assert.assertEquals(200, statusCode.value());
        Assert.assertSame(ssaSimpleResponse.getData(), optionalPatientList.get());
    }

    @Test
    public void testGetAllPatients2() throws Exception {

        final Optional<List<Patient>> optionalPatientList = Optional.empty();

        when(patientService.getAllPatients()).thenReturn(optionalPatientList);

        ResponseEntity result = patientRestController.getAllPatients();
        final HttpStatus statusCode = result.getStatusCode();
        final SsaSimpleResponse ssaSimpleResponse = (SsaSimpleResponse) result.getBody();
        final String responseMessage = ssaSimpleResponse.getMessage();

        //check actual request status
        Assert.assertEquals(200, statusCode.value());

        //check internal status
        Assert.assertNotNull(responseMessage);
        Assert.assertSame(Collections.EMPTY_LIST, ssaSimpleResponse.getData());
    }

    @Test
    public void testAddPatient() throws Exception {
        when(patientService.addPatient(any())).thenReturn(prepareSavedPatientResponse());

        ResponseEntity result = patientRestController.addPatient(prepareTestDataForaddPatient());

        final SsaSimpleResponse ssaSimpleResponse = (SsaSimpleResponse) result.getBody();

        Assert.assertNotNull(ssaSimpleResponse.getMessage().equals(IConstants.PATIENT_ADDED));
        Assert.assertSame(null, ssaSimpleResponse.getData());
    }

    @Test
    public void testAddPatient2() throws Exception {
        final Patient patient = prepareSavedPatientResponse();
        patient.setId(null);
        when(patientService.addPatient(any())).thenReturn(patient);

        ResponseEntity result = patientRestController.addPatient(prepareTestDataForaddPatient());
        final SsaSimpleResponse ssaSimpleResponse = (SsaSimpleResponse) result.getBody();

        Assert.assertNotNull(ssaSimpleResponse.getMessage().equals(IConstants.UNABLE_ADD_PATIENT));
        Assert.assertSame(null, ssaSimpleResponse.getData());
    }

    /**
     * @return optional list of sample patients
     */
    private Optional<List<Patient>> prepareTestDataForGetAllPatients() {
        Patient patient1 = new Patient();
        patient1.setGender(Gender.valueOf("MALE"));
        patient1.setName("Shenoy");
        patient1.setDayOfBirth("Sunday");

        Patient patient2 = new Patient();
        patient2.setGender(Gender.valueOf("FEMALE"));
        patient2.setName("Marry");
        patient2.setDayOfBirth("Wednesday");

        Patient patient3 = new Patient();
        patient3.setGender(Gender.NONE);
        patient3.setName("Bob");
        patient3.setDayOfBirth("Monday");

        return Optional.of(Arrays.asList(patient1, patient2, patient3));
    }

    /**
     * @return
     */
    private PatientDto prepareTestDataForaddPatient() {
        PatientDto patient1 = new PatientDto();
        patient1.setPatientGender("MALE");
        patient1.setPatientName("Shenoy");
        patient1.setDayOfBirth("Sunday");
        return patient1;
    }

    /**
     * @return
     */
    private Patient prepareSavedPatientResponse() {
        Patient patient1 = new Patient();
        patient1.setId(1L);
        patient1.setGender(Gender.valueOf("MALE"));
        patient1.setName("Shenoy");
        patient1.setDayOfBirth("Sunday");
        return patient1;
    }
}