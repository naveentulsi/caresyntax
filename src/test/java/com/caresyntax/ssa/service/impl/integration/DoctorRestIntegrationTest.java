package com.caresyntax.ssa.service.impl.integration;

import com.caresyntax.ssa.SsaApplication;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Doctor;
import com.caresyntax.ssa.utility.IConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SsaApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Log4j2
public class DoctorRestIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testDoctorApi_save_fetch() throws Exception {

        Doctor doctor = new Doctor();
        doctor.setName("James");

        try {
            final MvcResult postDoctor = mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/v1/doc").contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(doctor))).andReturn();

            final String postDoctorResponseString = postDoctor.getResponse().getContentAsString();
            final SsaSimpleResponse ssaSimpleResponse = readResponseAndCast(postDoctorResponseString);
            HashMap<String, String> responseMap = (HashMap<String, String>) ssaSimpleResponse.getData();
            final String doctorName = responseMap.get("name");

            // check doctor names is valid
            Assert.assertEquals(doctor.getName(), doctorName);


            // query inserted doctor name
            final MvcResult getAllDoc = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/doc")
                    .contentType(MediaType.APPLICATION_JSON)).andReturn();

            final String docListAsString = getAllDoc.getResponse().getContentAsString();
            final SsaSimpleResponse ssaSimpleResponseDocList = readResponseAndCast(docListAsString);
            final String message = ssaSimpleResponseDocList.getMessage();

            Assert.assertNotNull(message);
            Assert.assertEquals(message, IConstants.DOCTOR_FETCHED);

        } catch (Exception ex) {
            log.warn("Exception during Integration testing of doctor API");

            if(log.isErrorEnabled()){
                log.error(ex);
            }
        }

    }

    private SsaSimpleResponse readResponseAndCast(String responseString) {

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
