package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
public class InvoiceResponse {
    private Long id;
    private double amount;
    private List<Long> reservationIds;
}