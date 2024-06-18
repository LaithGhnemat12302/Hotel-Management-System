package com.alosh.security.Services;

import com.alosh.security.Dto.UpdateReservationRequest;
import com.alosh.security.Dto.ReservationResponse;
import com.alosh.security.Entity.Customer;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Entity.Room;
import com.alosh.security.Repositories.CustomerRepository;
import com.alosh.security.Repositories.ReservationRepository;
import com.alosh.security.Repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public ReservationResponse bookRoom(UpdateReservationRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());
        reservation.setCancelled(false);
        reservation.setCancellationApproved(false);

        Reservation savedReservation = reservationRepository.save(reservation);

        return new ReservationResponse(savedReservation.getId(), savedReservation.getCustomer().getId(),
                savedReservation.getRoom().getId(), savedReservation.getStartDate(),
                savedReservation.getEndDate(), savedReservation.isCancelled(),
                savedReservation.isCancellationApproved());
    }

    @Transactional
    public ReservationResponse modifyReservation(Long reservationId, UpdateReservationRequest request) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Room room = roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        reservation.setCustomer(customer);
        reservation.setRoom(room);
        reservation.setStartDate(request.getStartDate());
        reservation.setEndDate(request.getEndDate());

        Reservation updatedReservation = reservationRepository.save(reservation);

        return new ReservationResponse(updatedReservation.getId(), updatedReservation.getCustomer().getId(),
                updatedReservation.getRoom().getId(), updatedReservation.getStartDate(),
                updatedReservation.getEndDate(), updatedReservation.isCancelled(),
                updatedReservation.isCancellationApproved());
    }

    @Transactional
    public void requestCancellation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setCancelled(true);
        reservationRepository.save(reservation);
    }

    @Transactional
    public void approveCancellation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.isCancelled())
            throw new RuntimeException("Cancellation has not been requested");

        reservation.setCancellationApproved(true);
        reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }
}