package com.alosh.security.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReservationRequest {
    @NotNull
    private Long customerId;

    @NotNull
    private Long roomId;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;
}