package com.caresyntax.ssa.service;

import com.caresyntax.ssa.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRoomService<T> {
    Optional<List<T>> getAllRooms();
}
