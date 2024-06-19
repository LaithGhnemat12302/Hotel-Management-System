package com.alosh.security.Controller;

import com.alosh.security.Dto.AdminDTO;
import com.alosh.security.Services.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public ResponseEntity<AdminDTO> addAdmine(@RequestBody AdminDTO adminDTO) {
        AdminDTO addedAdmin = adminService.addAdmine(adminDTO);
        return ResponseEntity.ok(addedAdmin);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        List<AdminDTO> admins = adminService.getAdllEmployee();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable int id) {
        AdminDTO admin = adminService.getAdmin(id);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminDTO> updateAdmine(@PathVariable int id, @RequestBody AdminDTO adminDTO) {
        AdminDTO updatedAdmin = adminService.updateAdmine(id, adminDTO);
        return updatedAdmin != null ? ResponseEntity.ok(updatedAdmin) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> logIn(@RequestParam String email, @RequestParam String password) {
        String response = adminService.logIn(email, password);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AdminDTO> deleteEmployee(@PathVariable int id) {
        AdminDTO deletedAdmin = adminService.deleteEmployee(id);
        return deletedAdmin != null ? ResponseEntity.ok(deletedAdmin) : ResponseEntity.notFound().build();
    }

    @GetMapping("/employee/{username}")
    public ResponseEntity<AdminDTO> getOneEmployee(@PathVariable String username) {
        AdminDTO admin = adminService.getOneEmployee(username);
        return admin != null ? ResponseEntity.ok(admin) : ResponseEntity.notFound().build();
    }
}