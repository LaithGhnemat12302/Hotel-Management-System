package com.alosh.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.alosh.security.user.Permission.ADMIN_CREATE;
import static com.alosh.security.user.Permission.ADMIN_DELETE;
import static com.alosh.security.user.Permission.ADMIN_READ;
import static com.alosh.security.user.Permission.ADMIN_UPDATE;
import static com.alosh.security.user.Permission.CUSTOMER_CREATE;
import static com.alosh.security.user.Permission.CUSTOMER_DELETE;
import static com.alosh.security.user.Permission.CUSTOMER_READ;
import static com.alosh.security.user.Permission.CUSTOMER_UPDATE;

@RequiredArgsConstructor
public enum Role {

  USER(Collections.emptySet()),
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  CUSTOMER_READ,
                  CUSTOMER_UPDATE,
                  CUSTOMER_DELETE,
                  CUSTOMER_CREATE
          )
  ),
  CUSTOMER(
          Set.of(
                  CUSTOMER_READ,
                  CUSTOMER_UPDATE,
                  CUSTOMER_DELETE,
                  CUSTOMER_CREATE
          )
  )

  ;

  @Getter
  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
