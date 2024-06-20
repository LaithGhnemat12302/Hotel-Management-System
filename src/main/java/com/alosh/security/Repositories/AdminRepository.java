package com.alosh.security.Repositories;

import com.alosh.security.Entity.Admine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admine, Integer> {
    Admine findByEmailAndPassword(String email, String password);
    Admine findByUsername(String username);
}