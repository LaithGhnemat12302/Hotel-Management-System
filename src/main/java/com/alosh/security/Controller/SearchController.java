//package com.alosh.security.Controller;
//
//import com.alosh.security.Entity.Reservation;
//import com.alosh.security.Entity.Room;
//import com.alosh.security.Services.SearchService;
//import com.alosh.security.user.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/search")
//public class SearchController {
//
//    @Autowired
//    private SearchService searchService;
//
//    @GetMapping("/reservations")
//    public ResponseEntity<List<Reservation>> searchReservations(
//            @RequestParam(required = false) String customerName,
//            @RequestParam(required = false) Long customerId,
//            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
//        List<Reservation> reservations = searchService.searchReservations(customerName, customerId, date);
//        return ResponseEntity.ok(reservations);
//    }
//
//    @GetMapping("/customers/{id}")
//    public ResponseEntity<User> searchCustomer(@PathVariable Integer id) {
//        User customer = searchService.searchCustomer(id);
//        return ResponseEntity.ok(customer);
//    }
//
//    @GetMapping("/rooms")
//    public ResponseEntity<List<Room>> searchAvailableRooms(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
//        List<Room> rooms = searchService.searchAvailableRooms(date);
//        return ResponseEntity.ok(rooms);
//    }
//}
