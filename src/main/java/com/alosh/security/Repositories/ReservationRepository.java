package com.alosh.security.Repositories;

import com.alosh.security.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.rooms rm WHERE rm.id = :roomId AND r.endDate > :startDate AND r.startDate < :endDate")
    List<Reservation> findByRoomIdAndEndDateAfterAndStartDateBefore(
            @Param("roomId") Long roomId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :customerId AND :date BETWEEN r.startDate AND r.endDate")
    List<Reservation> findByCustomerIdAndDate(@Param("customerId") Long customerId, @Param("date") Date date);

    @Query("SELECT r FROM Reservation r WHERE r.customer.name = :customerName AND :date BETWEEN r.startDate AND r.endDate")
    List<Reservation> findByCustomerNameAndDate(@Param("customerName") String customerName, @Param("date") Date date);

    @Query("SELECT r FROM Reservation r WHERE :date BETWEEN r.startDate AND r.endDate")
    List<Reservation> findByDate(@Param("date") Date date);


    @Query("SELECT r FROM Reservation r WHERE r.customer.name = :customerName OR r.customer.id = :customerId")
    List<Reservation> findByCustomerNameOrCustomerId(@Param("customerName") String customerName, @Param("customerId") Long customerId);



    @Query("SELECT r FROM Reservation r WHERE r.customer.id = :customerId")
    List<Reservation> findByCustomerId(@Param("customerId") Long customerId);
}
