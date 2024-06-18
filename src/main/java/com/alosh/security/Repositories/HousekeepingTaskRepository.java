package com.alosh.security.Repositories;

import com.alosh.security.Entity.HousekeepingTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousekeepingTaskRepository extends JpaRepository<HousekeepingTask, Long> {
}
