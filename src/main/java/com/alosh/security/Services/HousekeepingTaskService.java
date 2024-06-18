//package com.alosh.security.Services;
//
//import com.alosh.security.Dto.HousekeepingTaskRequest;
//import com.alosh.security.Dto.HousekeepingTaskResponse;
//import com.alosh.security.Entity.Employee;
//import com.alosh.security.Entity.HousekeepingTask;
//import com.alosh.security.Repositories.EmployeeRepository;
//import com.alosh.security.Repositories.HousekeepingTaskRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class HousekeepingTaskService {
//    private final HousekeepingTaskRepository housekeepingTaskRepository;
//    private final EmployeeRepository employeeRepository;
//
////    @Transactional
//    public HousekeepingTaskResponse createTask(HousekeepingTaskRequest request) {
//        Employee employee = employeeRepository.findById(request.getAssignedEmployeeId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        HousekeepingTask task = new HousekeepingTask();
//        task.setDescription(request.getDescription());
//        task.setStatus(request.getStatus());
//        task.setAssignedEmployee(employee);
//
//        HousekeepingTask savedTask = housekeepingTaskRepository.save(task);
//
//        return new HousekeepingTaskResponse(savedTask.getId(), savedTask.getDescription(),
//                savedTask.getStatus(), savedTask.getAssignedEmployee().getId(),
//                savedTask.getAssignedEmployee().getName());
//    }
//
////    @Transactional
//    public HousekeepingTaskResponse updateTask(Long taskId, HousekeepingTaskRequest request) {
//        HousekeepingTask task = housekeepingTaskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException("Task not found"));
//
//        Employee employee = employeeRepository.findById(request.getAssignedEmployeeId())
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        task.setDescription(request.getDescription());
//        task.setStatus(request.getStatus());
//        task.setAssignedEmployee(employee);
//
//        HousekeepingTask updatedTask = housekeepingTaskRepository.save(task);
//
//        return new HousekeepingTaskResponse(updatedTask.getId(), updatedTask.getDescription(),
//                updatedTask.getStatus(), updatedTask.getAssignedEmployee().getId(),
//                updatedTask.getAssignedEmployee().getName());
//    }
//
//    public HousekeepingTaskResponse getTaskById(Long taskId) {
//        HousekeepingTask task = housekeepingTaskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException("Task not found"));
//
//        return new HousekeepingTaskResponse(task.getId(), task.getDescription(), task.getStatus(),
//                task.getAssignedEmployee().getId(), task.getAssignedEmployee().getName());
//    }
//
//    public List<HousekeepingTaskResponse> getAllTasks() {
//        return housekeepingTaskRepository.findAll().stream()
//                .map(task -> new HousekeepingTaskResponse(task.getId(), task.getDescription(),
//                        task.getStatus(), task.getAssignedEmployee().getId(),
//                        task.getAssignedEmployee().getName()))
//                        .collect(Collectors.toList());
//    }
//
//    public List<HousekeepingTaskResponse> getTasksByEmployeeId(Long employeeId) {
//        return housekeepingTaskRepository.findByAssignedEmployeeId(employeeId).stream()
//                .map(task -> new HousekeepingTaskResponse(task.getId(), task.getDescription(),
//                        task.getStatus(), task.getAssignedEmployee().getId(),
//                        task.getAssignedEmployee().getName()))
//                        .collect(Collectors.toList());
//    }
//
////    @Transactional
//    public void deleteTask(Long taskId) {
//        HousekeepingTask task = housekeepingTaskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException("Task not found"));
//
//        housekeepingTaskRepository.delete(task);
//    }
//}