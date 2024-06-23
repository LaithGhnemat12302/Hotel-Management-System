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

    @PostMapping
    public ResponseEntity<?> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        try {
            reservationService.reserveRoom(reservationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        Reservation reservation = reservationService.cancelReservation(id);
        return ResponseEntity.ok(reservation);
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



    @DeleteMapping("/delete/{id}")
    public void deleteReservation_byID(@PathVariable Long id) {
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

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Reservation>> getReservationsByCustomerId(@PathVariable Long customerId) {
        List<Reservation> reservations = reservationService.getReservationsByCustomerId(customerId);
        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reservations);
    }
}