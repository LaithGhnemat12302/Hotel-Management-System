package com.alosh.security.Controller;

import com.alosh.security.Dto.ReservationDto;
import com.alosh.security.Dto.ReservationRequest;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Services.ReservationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/api/v1/reservations")
@SecurityRequirement(name = "bearerAuth")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;
//Reservation
//    add reservation for a customer
@PostMapping
public ResponseEntity<?> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
    try {
        Reservation newReservation = reservationService.reserveRoom(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReservation);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        try {
            Reservation reservation = reservationService.updateReservation(id, updatedReservation);
            return ResponseEntity.ok(String.valueOf(reservation));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/all")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @DeleteMapping("/deleteRoomReservation/{reservationId}/{roomId}")
    public void deleteReservationForOneRoom(@PathVariable Long reservationId, @PathVariable Long roomId) {
        reservationService.deleteReservationForOneRoom(reservationId, roomId);
    }
}