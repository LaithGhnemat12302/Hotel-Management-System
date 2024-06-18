package com.alosh.security.Services;

import com.alosh.security.Dto.CustomerResponse;
import com.alosh.security.Dto.UpdateCustomerRequest;
import com.alosh.security.Entity.Customer;
import com.alosh.security.Repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerResponse registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer savedCustomer = customerRepository.save(customer);
        return new CustomerResponse(savedCustomer.getId(), savedCustomer.getName(), savedCustomer.getEmail());
    }

    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public CustomerResponse updateCustomerProfile(Long customerId, UpdateCustomerRequest customerDetails) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setName(customerDetails.getName());
        customer.setEmail(customerDetails.getEmail());
        if (customerDetails.getPassword() != null) {
            customer.setPassword(passwordEncoder.encode(customerDetails.getPassword()));
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return new CustomerResponse(updatedCustomer.getId(), updatedCustomer.getName(), updatedCustomer.getEmail());
    }

    public void changePassword(Long customerId, String newPassword) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);
    }

    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }
}
