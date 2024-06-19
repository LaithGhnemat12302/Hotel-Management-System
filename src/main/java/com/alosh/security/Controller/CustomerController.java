package com.alosh.security.Controller;

import com.alosh.security.Dto.CustomerResponse;
import com.alosh.security.Dto.LoginRequest;
import com.alosh.security.Dto.LoginResponse;
import com.alosh.security.Dto.UpdateCustomerRequest;
import com.alosh.security.Entity.Customer;
import com.alosh.security.Services.CustomerService;
//import com.alosh.security.auth.AuthenticationService;
//import com.alosh.security.config.JwtService;
import com.alosh.security.user.ChangePasswordRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
//    private final AuthenticationManager authenticationManager;
//    private final JwtService jwtService;
//    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<CustomerResponse> registerCustomer(@RequestBody Customer customer) {
        CustomerResponse savedCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }



    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerProfile(@PathVariable Long id) {
        return customerService.findById(id)
                .map(customer -> ResponseEntity.ok(new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomerProfile(
            @PathVariable Long id,
            @RequestBody UpdateCustomerRequest request
    ) {
        return ResponseEntity.ok(customerService.updateCustomerProfile(id, request));
    }

    @PostMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestBody ChangePasswordRequest request
    ) {
        customerService.changePassword(id, request.getNewPassword());
        return ResponseEntity.ok("Password updated successfully");
    }
}
