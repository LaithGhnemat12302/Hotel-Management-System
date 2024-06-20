package com.alosh.security.auth;

import com.alosh.security.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private Integer id;
  private String name;
  private String email;
  private String password;
  private Role role;
}
