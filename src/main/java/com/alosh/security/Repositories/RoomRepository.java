package com.alosh.security.Repositories;

import com.alosh.security.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAvailableRoomsByDate(Date date);

}