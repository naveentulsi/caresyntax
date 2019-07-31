package com.caresyntax.ssa.service.impl.integration;

import com.caresyntax.ssa.SsaApplication;
import com.caresyntax.ssa.dto.SsaSimpleResponse;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SsaApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
@Log4j2
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class ProcedureRestWithoutDataIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void test_no_procedure_response() {

        try {
            final List<String> findProcedureMessageList = getOperation("/api/v1/proc");
            final String findProcedureResponseMessage = findProcedureMessageList.get(0);

            Assert.assertNotNull(findProcedureResponseMessage);
            Assert.assertEquals(IConstants.PROCEDURE_FETCH_FAILED, findProcedureResponseMessage);

        } catch (JsonProcessingException jsEx) {
            log.warn("Json processing broke");

            if (log.isErrorEnabled()) {
                log.error(jsEx);
            }
        } catch (Exception ex) {
            log.warn("Unable to perform integration test on Procedure API");

            if (log.isErrorEnabled()) {
                log.error(ex);
            }
        }

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
