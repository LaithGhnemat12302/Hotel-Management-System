package com.alosh.security.Services;

import com.alosh.security.Dto.UpdateRoomRequest;
import com.alosh.security.Dto.RoomResponse;
import com.alosh.security.Entity.Room;
import com.alosh.security.Repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    @Transactional
    public RoomResponse addRoom(UpdateRoomRequest request) {
        Room room = new Room();
        room.setStatus(request.getStatus());
        room.setPrice(request.getPrice());
        room.setCapacity(request.getCapacity());
        room.setFeatures(request.getFeatures());

        Room savedRoom = roomRepository.save(room);
//          private Long id;
//
//    private String name;
//
//    private String status;
//
//    private double price;
//
//    private int capacity;
//
//    private String features;

        return new RoomResponse(savedRoom.getId(), savedRoom.getName(), savedRoom.getStatus(),
                savedRoom.getPrice(), savedRoom.getCapacity(), savedRoom.getFeatures());
    }

    @Transactional
    public RoomResponse updateRoom(Long roomId, UpdateRoomRequest request) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));


        room.setStatus(request.getStatus());
        room.setPrice(request.getPrice());
        room.setCapacity(request.getCapacity());
        room.setFeatures(request.getFeatures());

        Room updatedRoom = roomRepository.save(room);

        return new RoomResponse(updatedRoom.getId(), updatedRoom.getName(), updatedRoom.getStatus(),
                updatedRoom.getPrice(), updatedRoom.getCapacity(), updatedRoom.getFeatures());
    }

    public RoomResponse getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return new RoomResponse(room.getId(), room.getName(), room.getStatus(), room.getPrice(),
                room.getCapacity(), room.getFeatures());
    }

    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(room -> new RoomResponse(room.getId(), room.getName(), room.getStatus(),
                        room.getPrice(), room.getCapacity(), room.getFeatures()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        roomRepository.delete(room);
    }

    public List<Room> findAvailableRoomsByDateRange(Date startDate, Date endDate) {
        return roomRepository.findAvailableRoomsByDateRange(startDate, endDate);
    }
    public List<Room> getAllNotReservedRooms() {
        return roomRepository.findAllNotReserved();
    }
}