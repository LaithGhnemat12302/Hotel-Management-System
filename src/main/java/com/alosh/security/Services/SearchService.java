package com.alosh.security.Services;

import com.alosh.security.Dto.CustomerDTO;
import com.alosh.security.Dto.RoomDTO;
import com.alosh.security.Dto.customReservationDTO;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Entity.Room;
import com.alosh.security.Repositories.ReservationRepository;
import com.alosh.security.Repositories.RoomRepository;
import com.alosh.security.user.User;
import com.alosh.security.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SearchService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Reservation> searchReservations(String customerName, Long customerId, Date date) {
        if (customerName != null) {
            return reservationRepository.findByCustomerNameAndDate(customerName, date);
        } else if (customerId != null) {
            return reservationRepository.findByCustomerIdAndDate(customerId, date);
        } else {
            return reservationRepository.findByDate(date);
        }
    }

    public User searchCustomer(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<Room> searchAvailableRooms(Date startDate, Date endDate) {
        return roomRepository.findAvailableRoomsByDateRange(startDate, endDate);
    }


    public List<customReservationDTO> searchReservationsByCustomer(String customerName, Long customerId) {
        List<Reservation> reservations = reservationRepository.findByCustomerNameOrCustomerId(customerName, customerId);
        return reservations.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<customReservationDTO> getReservationsByDate(Date date) {
        List<Reservation> reservations = reservationRepository.findByDate(date);
        return reservations.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private customReservationDTO convertToDto(Reservation reservation) {
        return customReservationDTO.builder()
                .id(reservation.getId())
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .status(reservation.getStatus())
                .customer(new CustomerDTO(
                        reservation.getCustomer().getId(),
                        reservation.getCustomer().getName()
                        // add other fields as needed
                ))
                .rooms(reservation.getRooms().stream().map(room -> new RoomDTO(
                        room.getId(),
                        room.getName(),
                        room.getPrice(),
                        room.getFacilities(),
                        room.getCapacity(),
                        room.getSize(),
                        room.getFeatures()
                )).collect(Collectors.toList()))
                .build();
    }
}