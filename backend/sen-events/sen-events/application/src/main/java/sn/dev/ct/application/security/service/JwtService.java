package sn.dev.ct.application.security.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Map;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extractClaims, UserDetails userDetails);
    boolean isTokenExpired(String token);
    Date extractExpirationDate(String token);
    boolean isTokenValid(String token, UserDetails userDetails);

}
