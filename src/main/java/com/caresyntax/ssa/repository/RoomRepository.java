package com.caresyntax.ssa.repository;

import com.caresyntax.ssa.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
