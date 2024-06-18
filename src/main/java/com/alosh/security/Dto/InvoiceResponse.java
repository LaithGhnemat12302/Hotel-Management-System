package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceResponse {
    private Long id;

    private double amount;

    private Long reservationId;

    private String customerName;

    private String roomType;
}