package com.alosh.security.Repositories;

import com.alosh.security.Entity.HousekeepingTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HousekeepingTaskRepository extends JpaRepository<HousekeepingTask, Long> {
    List<HousekeepingTask> findByAssignedEmployeeId(Long employeeId);
}