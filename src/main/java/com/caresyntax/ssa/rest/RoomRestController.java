package com.caresyntax.ssa.rest;

import com.caresyntax.ssa.dto.SsaSimpleResponse;
import com.caresyntax.ssa.model.Room;
import com.caresyntax.ssa.service.IRoomService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/v1/room")
@Log4j2
public class RoomRestController {

    IRoomService roomService;

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(name = "Get all rooms", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SsaSimpleResponse> getAllRooms() {
        log.info("START RoomRestController getAllRooms");
        SsaSimpleResponse ssaSimpleResponse = new SsaSimpleResponse();
        ssaSimpleResponse.setMessage("Unable to fetch rooms.");
        final Optional allRooms = this.roomService.getAllRooms();

        if (allRooms.isPresent()) {
            ssaSimpleResponse.setMessage("Rooms has been fetched");
            final List<Room> roomList = (List<Room>) allRooms.get();

            ssaSimpleResponse.setData(roomList);
            log.info(String.format("Total rooms fetched %d", roomList.size()));
        }

        log.info("END RoomRestController getAllRooms");
        return ResponseEntity.ok(ssaSimpleResponse);
    }
}
