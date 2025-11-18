package sn.dev.ct.application.security.service;

import org.springframework.web.bind.annotation.RequestBody;
import sn.dev.ct.application.security.dto.JwtResponse;
import sn.dev.ct.application.security.dto.SignInRequest;

import java.util.Map;

public interface AuthenticationService {
    JwtResponse authenticate(SignInRequest signInRequest);
    JwtResponse refresh(Map<String, String> request);
    void logout(@RequestBody Map<String, String> request);
}
