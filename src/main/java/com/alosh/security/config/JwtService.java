package com.alosh.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * a component responsible for various operations related to
 * JWT (JSON Web Tokens) in a Spring Boot application.
 * It contains methods for generating, parsing, and validating JWT tokens.
 */
@Service
public class JwtService {

//  a secret cryptographic key used to sign and verify JWTs
  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }
  /**
   *
   * @param token
   * @param claimsResolver
   * @return
   * @param <T>
   */
  //A claim is a piece of information that is
  // included within a JSON Web Token (JWT).
  // It is a key-value pair that provides
  // additional data about the user or the
  // token itself. Claims can be used to store
  // various types of information,
  // such as the user's name, email, roles,
  // or token expiration time.
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }
//
//  public String generateToken(
//      Map<String, Object> extraClaims,
//      UserDetails userDetails
//  ) {
//    return buildToken(extraClaims, userDetails, jwtExpiration);
//  }

  public String generateToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails
  ) {
    String role = extractUserRole(userDetails);
    extraClaims.put("role", role);
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public String generateRefreshToken(
      UserDetails userDetails
  ) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken(
          Map<String, Object> extraClaims,
          UserDetails userDetails,
          long expiration
  ) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }


  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private String extractUserRole(UserDetails userDetails) {
    for (GrantedAuthority authority : userDetails.getAuthorities()) {
      // Assuming the role name is the authority without the "ROLE_" prefix
      String authorityName = authority.getAuthority().replace("ROLE_", "");
      return authorityName; // Return the first role found
    }
    return "CUSTOMER"; // Default to "USER" role if no roles are found
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
