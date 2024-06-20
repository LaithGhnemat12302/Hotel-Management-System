package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class InvoiceRequest {
    private double amount;
    private List<Long> reservationIds;
}