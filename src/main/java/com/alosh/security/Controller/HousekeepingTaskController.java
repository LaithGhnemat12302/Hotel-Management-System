package com.alosh.security.Controller;

import com.alosh.security.Dto.HousekeepingTaskRequest;
import com.alosh.security.Dto.HousekeepingTaskResponse;
import com.alosh.security.Services.HousekeepingTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/housekeeping-tasks")
@RequiredArgsConstructor
public class HousekeepingTaskController {
    private final HousekeepingTaskService housekeepingTaskService;

    @PostMapping
    public ResponseEntity<HousekeepingTaskResponse> createTask(
            @RequestBody HousekeepingTaskRequest request
    )

    {
        HousekeepingTaskResponse response = housekeepingTaskService.createTask(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HousekeepingTaskResponse> updateTask(
            @PathVariable Long id,
            @RequestBody HousekeepingTaskRequest request
    )

    {
        HousekeepingTaskResponse response = housekeepingTaskService.updateTask(id, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HousekeepingTaskResponse> getTaskById(@PathVariable Long id) {
        HousekeepingTaskResponse response = housekeepingTaskService.getTaskById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<HousekeepingTaskResponse>> getAllTasks() {
        List<HousekeepingTaskResponse> response = housekeepingTaskService.getAllTasks();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<HousekeepingTaskResponse>> getTasksByEmployeeId(
            @PathVariable Long employeeId
    )

    {
        List<HousekeepingTaskResponse> response = housekeepingTaskService.getTasksByEmployeeId(employeeId);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        housekeepingTaskService.deleteTask(id);

        return ResponseEntity.ok("Task deleted successfully");
    }
}