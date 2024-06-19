package com.alosh.security.auth;

import com.alosh.security.Entity.Admine;
import com.alosh.security.Entity.Admine;
import com.alosh.security.Entity.Customer;
import com.alosh.security.Repositories.AdminRepository;
import com.alosh.security.Repositories.CustomerRepository;
import com.alosh.security.SecurityApplication;
import com.alosh.security.config.JwtService;
import com.alosh.security.token.Token;
import com.alosh.security.token.TokenRepository;
import com.alosh.security.token.TokenType;
import com.alosh.security.user.Role;
import com.alosh.security.user.User;
import com.alosh.security.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;







@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final CustomerRepository customerRepository;
  private final UserRepository userRepository;
  private final AdminRepository adminRepository;

  public AuthenticationResponse register(RegisterRequest request) {
    // Check if the email already exists
    var existingUser = userRepository.findByEmail(request.getEmail());
    if (existingUser.isPresent()) {
      // If the user already exists, return the user details
      var user = existingUser.get();
      var jwtToken = jwtService.generateToken(user);
      var refreshToken = jwtService.generateRefreshToken(user);
      return AuthenticationResponse.builder()
              .accessToken(jwtToken)
              .refreshToken(refreshToken)
              .build();
    }

    var user = User.builder()

            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(request.getRole())
            .build();
    User savedUser = userRepository.save(user);
    if ("CUSTOMER".equalsIgnoreCase(String.valueOf(request.getRole()))) {
      Customer customer = Customer.builder()
              .user(savedUser)
              .name(request.getName())
              .email(request.getEmail())
              .password(passwordEncoder.encode(request.getPassword())) // or save without encoding if already encoded
              .build();
      customerRepository.save(customer);
    } else if ("ADMIN".equalsIgnoreCase(request.getRole().name())) {
      Admine admin = Admine.builder()
              .username(request.getName())
              .password(passwordEncoder.encode(request.getPassword()))
              .email(request.getEmail())
              .role("ADMIN")
              .build();
      adminRepository.save(admin);
    }




    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var user = repository.findByEmail(request.getEmail())
            .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    var refreshToken = jwtService.generateRefreshToken(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken);
    return AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build();
  }

  private void saveUserToken(User user, String jwtToken) {
    var token = Token.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}



















//@Service
//@RequiredArgsConstructor
//public class AuthenticationService {
//  private final UserRepository repository;
//  private final TokenRepository tokenRepository;
//  private final PasswordEncoder passwordEncoder;
//  private final JwtService jwtService;
//  private final AuthenticationManager authenticationManager;
//  private final CustomerRepository customerRepository;
//  private static final Logger logger = LoggerFactory.getLogger(SecurityApplication.class);
//
//
//  public AuthenticationResponse register(RegisterRequest request) {
//    logger.debug("Register method called with request: {}", request);
//
//    var user = User.builder()
//            .name(request.getName())
//            .email(request.getEmail())
//            .password(passwordEncoder.encode(request.getPassword()))
//            .role(request.getRole())
//            .build();
//    logger.debug("User object built: {}", user);
//
//    var savedUser = repository.save(user);
//    logger.debug("User saved: {}", savedUser);
//
//    var jwtToken = jwtService.generateToken(user);
//    var refreshToken = jwtService.generateRefreshToken(user);
//    saveUserToken(savedUser, jwtToken);
//
//    logger.debug("Application before if get role !");
//    if (savedUser.getRole() == Role.CUSTOMER) {
//      logger.debug("Application inside if get role !");
//
//      Customer customer = Customer.builder()
//              .user(savedUser)
//              .name(request.getName())
//              .email(request.getEmail())
//              .password(request.getPassword())
//              .build();
//      customerRepository.save(customer);
//      logger.debug("Customer saved: {}", customer);
//    } else {
//      logger.debug("Role is not CUSTOMER, it is: {}", savedUser.getRole());
//    }
//
//    return AuthenticationResponse.builder()
//            .accessToken(jwtToken)
//            .refreshToken(refreshToken)
//            .build();
//  }
//
//  public AuthenticationResponse authenticate(AuthenticationRequest request) {
//    //Attempts to authenticate the passed Authentication object, returning a
//    // fully populated Authentication object (including granted authorities) if successful.
//    authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//            request.getEmail(),
//            request.getPassword()
//        )
//    );
//    var user = repository.findByEmail(request.getEmail())
//        .orElseThrow();
//    var jwtToken = jwtService.generateToken(user);
//    var refreshToken = jwtService.generateRefreshToken(user);
//    revokeAllUserTokens(user);
//    saveUserToken(user, jwtToken);
//    return AuthenticationResponse.builder()
//        .accessToken(jwtToken)
//            .refreshToken(refreshToken)
//        .build();
//  }
//
//
//
//  private void saveUserToken(User user, String jwtToken) {
//    var token = Token.builder()
//        .user(user)
//        .token(jwtToken)
//        .tokenType(TokenType.BEARER)
//        .expired(false)
//        .revoked(false)
//        .build();
//    tokenRepository.save(token);
//  }
//
//  private void revokeAllUserTokens(User user) {
//    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
//    if (validUserTokens.isEmpty())
//      return;
//    validUserTokens.forEach(token -> {
//      token.setExpired(true);
//      token.setRevoked(true);
//    });
//    tokenRepository.saveAll(validUserTokens);
//  }
//
//  public void refreshToken(
//          HttpServletRequest request,
//          HttpServletResponse response
//  ) throws IOException {
//    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//    final String refreshToken;
//    final String userEmail;
//    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
//      return;
//    }
//    refreshToken = authHeader.substring(7);
//    userEmail = jwtService.extractUsername(refreshToken);
//    if (userEmail != null) {
//      var user = this.repository.findByEmail(userEmail)
//              .orElseThrow();
//      if (jwtService.isTokenValid(refreshToken, user)) {
//        var accessToken = jwtService.generateToken(user);
//        revokeAllUserTokens(user);
//        saveUserToken(user, accessToken);
//        var authResponse = AuthenticationResponse.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
//      }
//    }
//  }
//}
