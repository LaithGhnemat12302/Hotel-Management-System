package com.alosh.security.Controller;

import com.alosh.security.Dto.EmployeeResponse;
import com.alosh.security.Dto.UpdateEmployeeRequest;
import com.alosh.security.Entity.Employee;
import com.alosh.security.Services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> registerEmployee(@Valid @RequestBody Employee employee) {
        EmployeeResponse savedEmployee = employeeService.registerEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeProfile(@PathVariable Long id) {
        Employee employee = employeeService.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        return ResponseEntity.ok(new EmployeeResponse(employee.getId(), employee.getName(), employee.getRole()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployeeProfile(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeRequest request
    ) {
        return ResponseEntity.ok(employeeService.updateEmployeeProfile(id, request));
    }
}
