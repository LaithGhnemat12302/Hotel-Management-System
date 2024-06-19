package com.alosh.security.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationDto {

    private int id;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;
    private String status;
    private List<Integer> roomnumber;
}