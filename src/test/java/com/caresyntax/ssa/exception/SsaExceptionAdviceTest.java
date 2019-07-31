package com.caresyntax.ssa.exception;

import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class SsaExceptionAdviceTest {
    @Mock
    Logger log;
    @InjectMocks
    SsaExceptionAdvice ssaExceptionAdvice;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHandleAll() throws Exception {
        ResponseEntity result = ssaExceptionAdvice.HandleAll(null);
        final SsaExceptionResponse ssaExceptionResponse = new SsaExceptionResponse("Server Exception", "Server is down, try again in some time.", true);
        final SsaExceptionResponse actualResponse = (SsaExceptionResponse) result.getBody();

        Assert.assertEquals(ssaExceptionResponse.getError(), actualResponse.getError());
        Assert.assertEquals(ssaExceptionResponse.getMessage(), actualResponse.getMessage());
    }

    @Test
    public void testHandleInvalidDataException() throws Exception {

        SsaInvalidDataException ssaInvalidDataException = new SsaInvalidDataException("No valid fields", "Demo field is required");
        ResponseEntity result = ssaExceptionAdvice.HandleInvalidDataException(ssaInvalidDataException);
        final SsaExceptionResponse resultBody = (SsaExceptionResponse) result.getBody();

        Assert.assertEquals(ssaInvalidDataException.getMessage(), resultBody.getMessage());
    }
}