package com.alosh.security.Controller;

import com.alosh.security.Dto.customReservationDTO;
import com.alosh.security.Entity.Reservation;
import com.alosh.security.Entity.Room;
import com.alosh.security.Services.SearchService;
import com.alosh.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    private SearchService searchService;



    @GetMapping("/reservations")
    public ResponseEntity<?> searchReservations(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) Long customerId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        List<customReservationDTO> reservations = searchService.searchReservationsByCustomer(customerName, customerId);
        if (reservations.isEmpty()) {
            return ResponseEntity.status(404).body("There are no reservations for the given customer.");
        }
        return ResponseEntity.ok(reservations);
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<User> searchCustomer(@PathVariable Integer id) {
        User user = searchService.searchCustomer(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> searchAvailableRooms(
            @RequestParam Date startDate,
            @RequestParam Date endDate
    ) {
        List<Room> rooms = searchService.searchAvailableRooms(startDate, endDate);
        return ResponseEntity.ok(rooms);
    }
}
