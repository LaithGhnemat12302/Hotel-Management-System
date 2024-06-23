package com.alosh.security.Controller;

import com.alosh.security.Dto.UpdateRoomRequest;
import com.alosh.security.Dto.RoomResponse;
import com.alosh.security.Entity.Room;
import com.alosh.security.Services.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomResponse> addRoom(@Valid @RequestBody UpdateRoomRequest request) {
        RoomResponse response = roomService.addRoom(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponse> updateRoom(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRoomRequest request
    ) {
        RoomResponse response = roomService.updateRoom(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponse> getRoomById(@PathVariable Long id) {
        RoomResponse response = roomService.getRoomById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAllRooms() {
        List<RoomResponse> response = roomService.getAllRooms();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    @GetMapping("/available")
    public ResponseEntity<List<Room>> findAvailableRooms(@RequestParam Date startDate, @RequestParam Date endDate) {
        List<Room> availableRooms = roomService.findAvailableRoomsByDateRange(startDate, endDate);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/not-reserved")
    public ResponseEntity<List<Room>> getAllNotReservedRooms() {
        List<Room> rooms = roomService.getAllNotReservedRooms();
        return ResponseEntity.ok(rooms);
    }
}