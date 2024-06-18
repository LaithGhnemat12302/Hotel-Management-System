package com.alosh.security.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HousekeepingTaskRequest {
    private String description;

    private String status;

    private Long assignedEmployeeId;
}