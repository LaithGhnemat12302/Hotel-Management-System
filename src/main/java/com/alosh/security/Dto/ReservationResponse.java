package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private Long id;

    private Long customerId;

    private Long roomId;

    private Date startDate;

    private Date endDate;

    private boolean isCancelled;

    private boolean isCancellationApproved;
}