package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.service.IRoomService;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class RoomRestControllerTest {
    @Mock
    IRoomService roomService;
    @Mock
    Logger log;
    @InjectMocks
    RoomRestController roomRestController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRooms() throws Exception {
        when(roomService.getAllRooms()).thenReturn(null);

        ResponseEntity<SsaSimpleResponse> result = roomRestController.getAllRooms();
        Assert.assertEquals(null, result);
    }
}