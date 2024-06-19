package com.alosh.security.Services;

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
}