package com.alosh.security.Repositories;

import com.alosh.security.Entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


import java.util.Date;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("SELECT r FROM Room r WHERE r.id NOT IN (SELECT rm.id FROM Reservation res JOIN res.rooms rm WHERE res.endDate > :startDate AND res.startDate < :endDate)")
    List<Room> findAvailableRoomsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
