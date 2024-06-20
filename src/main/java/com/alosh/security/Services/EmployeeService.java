package com.alosh.security.Services;

import com.alosh.security.Dto.EmployeeResponse;
import com.alosh.security.Dto.UpdateEmployeeRequest;
import com.alosh.security.Entity.Employee;
import com.alosh.security.Errors.EmployeeNotFoundException;
import com.alosh.security.Repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));

        employee.setName(employeeDetails.getName());
        employee.setSalary(employeeDetails.getSalary());
        employee.setNationalNumber(employeeDetails.getNationalNumber());

        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found with id " + id));
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
}