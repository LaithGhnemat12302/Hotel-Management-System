package com.alosh.security.Services;

import com.alosh.security.Entity.Reservation;
import com.alosh.security.Repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation reserveRoom(Reservation reservation) {
        List<Reservation> overlappingReservations = reservationRepository.findByRoomIdAndEndDateAfterAndStartDateBefore(
                reservation.getRoom().getId(),
                reservation.getStartDate(),
                reservation.getEndDate()
        );

        if (!overlappingReservations.isEmpty()) {
            throw new RuntimeException("The room is already reserved for the requested period.");
        }

        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> existingReservationOptional = reservationRepository.findById(id);
        if (existingReservationOptional.isPresent()) {
            Reservation existingReservation = existingReservationOptional.get();
            existingReservation.setStartDate(updatedReservation.getStartDate());
            existingReservation.setEndDate(updatedReservation.getEndDate());
            existingReservation.setRoom(updatedReservation.getRoom());
            return reservationRepository.save(existingReservation);
        } else {
            throw new RuntimeException("Reservation not found with id " + id);
        }
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
