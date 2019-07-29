package com.caresyntax.ssa.service.impl;

import com.caresyntax.ssa.model.Room;
import com.caresyntax.ssa.repository.RoomRepository;
import com.caresyntax.ssa.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService implements IRoomService<Room> {

    RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Optional<List<Room>> getAllRooms() {
        final List<Room> roomList = this.roomRepository.findAll();
        return Optional.of(roomList);
    }
}
