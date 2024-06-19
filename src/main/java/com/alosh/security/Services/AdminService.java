package com.alosh.security.Services;

import com.alosh.security.Dto.AdminDTO;
import com.alosh.security.Entity.Admine;
import com.alosh.security.Entity.Employee;
import com.alosh.security.Repositories.AdminRepository;
import com.alosh.security.Repositories.EmployeeRepository;
import com.alosh.security.Services.Interface.AdminInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService implements AdminInterface {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public AdminDTO addAdmine(AdminDTO adminDTO) {
        Admine admin = convertToEntity(adminDTO);
        Admine savedAdmin = adminRepository.save(admin);
        return convertToDTO(savedAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDTO> getAdllEmployee() {
        List<Admine> admins = adminRepository.findAll();
        return admins.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDTO getAdmin(int id) {
        Optional<Admine> admin = adminRepository.findById(id);
        return admin.map(this::convertToDTO).orElse(null);
    }

    @Override
    @Transactional
    public AdminDTO updateAdmine(int id, AdminDTO adminDTO) {
        Optional<Admine> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admine admin = adminOptional.get();
            admin.setUsername(adminDTO.getUsername());
            admin.setPassword(adminDTO.getPassword());
            admin.setFirstname(adminDTO.getFirstname());
            admin.setLastname(adminDTO.getLastname());
            admin.setEmail(adminDTO.getEmail());
            admin.setPhone(adminDTO.getPhone());
            admin.setRole(adminDTO.getRole());
            admin.setSalary(adminDTO.getSalary());
            admin.setAge(adminDTO.getAge());
            Admine updatedAdmin = adminRepository.save(admin);
            return convertToDTO(updatedAdmin);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public String logIn(String email, String password) {
        Admine admin = adminRepository.findByEmailAndPassword(email, password);
        return admin != null ? "Login successful" : "Invalid credentials";
    }

    @Override
    @Transactional
    public AdminDTO insertEmployee(int idAdmin, AdminDTO adminDTO) {
        Optional<Admine> adminOptional = adminRepository.findById(idAdmin);
        if (adminOptional.isPresent()) {
            Employee employee = new Employee();
            employee.setName(adminDTO.getFirstname() + " " + adminDTO.getLastname());
            employee.setSalary(adminDTO.getSalary());
            employee.setNationalNumber(adminDTO.getUsername());  // Example logic
            employeeRepository.save(employee);
            return adminDTO;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public AdminDTO getOneEmployee(String username) {
        Admine admin = adminRepository.findByUsername(username);
        return convertToDTO(admin);
    }

    @Override
    @Transactional
    public AdminDTO deleteEmployee(int id) {
        Optional<Admine> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            adminRepository.deleteById(id);
            return convertToDTO(admin.get());
        }
        return null;
    }

    @Override
    @Transactional
    public AdminDTO updateEmployee(int id, AdminDTO adminDTO) {
        return updateAdmine(id, adminDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AdminDTO> getAllAdmine() {
        return getAdllEmployee();
    }

    private Admine convertToEntity(AdminDTO adminDTO) {
        Admine admin = new Admine();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        admin.setFirstname(adminDTO.getFirstname());
        admin.setLastname(adminDTO.getLastname());
        admin.setEmail(adminDTO.getEmail());
        admin.setPhone(adminDTO.getPhone());
        admin.setRole(adminDTO.getRole());
        admin.setSalary(adminDTO.getSalary());
        admin.setAge(adminDTO.getAge());
        return admin;
    }

    private AdminDTO convertToDTO(Admine admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setPassword(admin.getPassword());
        adminDTO.setFirstname(admin.getFirstname());
        adminDTO.setLastname(admin.getLastname());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setRole(admin.getRole());
        adminDTO.setSalary(admin.getSalary());
        adminDTO.setAge(admin.getAge());
        return adminDTO;
    }
}