package com.alosh.security.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationRequest {
    private Date startDate;
    private Date endDate;
    private String status;
    private CustomerDTO customer;
    private RoomDTO room;

    @Data
    public static class CustomerDTO {
        private Long id;
    }

    @Data
    public static class RoomDTO {
        private Long id;
    }
}
