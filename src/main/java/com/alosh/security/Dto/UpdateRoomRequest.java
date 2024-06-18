package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoomRequest {
    private String type;

    private String status;

    private double price;

    private int capacity;

    private String features;
}