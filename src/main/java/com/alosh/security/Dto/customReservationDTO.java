package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class customReservationDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private String status;
    private CustomerDTO customer;
    private List<RoomDTO> rooms;
}
