package com.alosh.security.Repositories;

import com.alosh.security.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCustomerNameAndDate(String userName, Date date);

    List<Reservation> findByCustomerIdAndDate(Long userId, Date date);

    List<Reservation> findByRoomIdAndEndDateAfterAndStartDateBefore(Long roomId, Date startDate, Date endDate);
}
