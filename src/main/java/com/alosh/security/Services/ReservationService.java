package com.alosh.security.Services;

import com.alosh.security.Dto.ReservationDto;
import com.alosh.security.Dto.ReservationRequest;
import com.alosh.security.Entity.Customer;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Entity.Room;
import com.alosh.security.Repositories.CustomerRepository;
import com.alosh.security.Repositories.ReservationRepository;
import com.alosh.security.Repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;



@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Transactional
    public Reservation reserveRoom(ReservationRequest reservationRequest) {
        logger.info("Starting room reservation for customer ID: {} and room ID: {}",
                reservationRequest.getCustomer().getId(), reservationRequest.getRoom().getId());

        Optional<Customer> customerOptional = customerRepository.findById(reservationRequest.getCustomer().getId());
        Optional<Room> roomOptional = roomRepository.findById(reservationRequest.getRoom().getId());

        if (customerOptional.isEmpty()) {
            String message = "Customer not found with ID: " + reservationRequest.getCustomer().getId();
            logger.error(message);
            throw new RuntimeException(message);
        }

        if (roomOptional.isEmpty()) {
            String message = "Room not found with ID: " + reservationRequest.getRoom().getId();
            logger.error(message);
            throw new RuntimeException(message);
        }

        Room room = roomOptional.get();
        logger.info("Room found: {}", room);



        Reservation reservation = Reservation.builder()
                .startDate(reservationRequest.getStartDate())
                .endDate(reservationRequest.getEndDate())
                .status(reservationRequest.getStatus())
                .customer(customerOptional.get())
                .rooms(List.of(room))
                .build();

        Reservation savedReservation = reservationRepository.save(reservation);
        logger.info("Reservation successfully created: {}", savedReservation);

        return savedReservation;
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Optional<Reservation> existingReservationOptional = reservationRepository.findById(id);
        if (existingReservationOptional.isPresent()) {
            Reservation existingReservation = existingReservationOptional.get();
            existingReservation.setStartDate(updatedReservation.getStartDate());
            existingReservation.setEndDate(updatedReservation.getEndDate());
            existingReservation.setCustomer(updatedReservation.getCustomer());
            existingReservation.setRooms(updatedReservation.getRooms());
            existingReservation.setStatus(updatedReservation.getStatus());
            return reservationRepository.save(existingReservation);
        } else {
            throw new RuntimeException("Reservation not found with id " + id);
        }
    }

    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservationForOneRoom(Long reservationId, Long roomId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.getRooms().removeIf(room -> room.getId().equals(roomId));
            reservationRepository.save(reservation);
        } else {
            throw new RuntimeException("Reservation not found with id " + reservationId);
        }
    }

    public void deleteReservation(Long reservationId) {
        if (reservationRepository.existsById(reservationId)) {
            reservationRepository.deleteById(reservationId);
        } else {
            throw new RuntimeException("Reservation not found with id " + reservationId);
        }
    }
}