package com.alosh.security.Controller;

import com.alosh.security.Dto.UpdateReservationRequest;
import com.alosh.security.Dto.ReservationResponse;
import com.alosh.security.Services.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/book")
    public ResponseEntity<ReservationResponse> bookRoom(@Valid @RequestBody UpdateReservationRequest request) {
        ReservationResponse response = reservationService.bookRoom(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<ReservationResponse> modifyReservation(
            @PathVariable Long id,
            @Valid @RequestBody UpdateReservationRequest request
    )

    {
        ReservationResponse response = reservationService.modifyReservation(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancel/{id}")
    public ResponseEntity<String> requestCancellation(@PathVariable Long id) {
        reservationService.requestCancellation(id);
        return ResponseEntity.ok("Cancellation requested successfully");
    }

    @PostMapping("/approve-cancellation/{id}")
    public ResponseEntity<String> approveCancellation(@PathVariable Long id) {
        reservationService.approveCancellation(id);
        return ResponseEntity.ok("Cancellation approved successfully");
    }
}