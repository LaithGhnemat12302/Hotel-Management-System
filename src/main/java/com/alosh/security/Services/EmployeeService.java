package com.alosh.security.Services;

import com.alosh.security.Dto.EmployeeResponse;
import com.alosh.security.Dto.UpdateEmployeeRequest;
import com.alosh.security.Entity.Employee;
import com.alosh.security.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public EmployeeResponse registerEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        return new EmployeeResponse(savedEmployee.getId(), savedEmployee.getName(), savedEmployee.getRole());
    }

    public Optional<Employee> findByName(String name) {
        return employeeRepository.findByName(name);
    }

    @Transactional
    public EmployeeResponse updateEmployeeProfile(Long employeeId, UpdateEmployeeRequest employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        employee.setName(employeeDetails.getName());
        employee.setRole(employeeDetails.getRole());

        Employee updatedEmployee = employeeRepository.save(employee);
        return new EmployeeResponse(updatedEmployee.getId(), updatedEmployee.getName(), updatedEmployee.getRole());
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
}