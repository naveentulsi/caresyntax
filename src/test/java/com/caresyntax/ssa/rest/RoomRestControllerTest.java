package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Room;
import com.caresyntax.ssa.service.IRoomService;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

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

    /**
     * Validate room data and the response message.
     * @throws Exception
     */
    @Test
    public void testGetAllRooms() throws Exception {

        when(roomService.getAllRooms()).thenReturn(Optional.of(Arrays.asList(prepareTestDate())));

        ResponseEntity<SsaSimpleResponse> result = roomRestController.getAllRooms();
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Rooms has been fetched", result.getBody().getMessage());
    }

    /**
     * Validate room data and the negative response message.
     * @throws Exception
     */
    @Test
    public void testGetAllRoomsFailure() throws Exception {

        when(roomService.getAllRooms()).thenReturn(Optional.empty());

        ResponseEntity<SsaSimpleResponse> result = roomRestController.getAllRooms();
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals("Unable to fetch rooms.", result.getBody().getMessage());
    }

    /**
     * prepare test data for room controller
     * @return room instance
     */
    private Room prepareTestDate(){
        Room room = new Room();
        room.setId(1);
        room.setName("Ward 1");
        return room;
    }
}