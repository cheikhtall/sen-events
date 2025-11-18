package sn.dev.ct.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.dev.ct.application.security.dto.JwtResponse;
import sn.dev.ct.application.security.dto.SignInRequest;
import sn.dev.ct.application.security.service.AuthenticationService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {
    private final AuthenticationService authenticationService;

    public AuthApi(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signin(@RequestBody SignInRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public JwtResponse refresh(@RequestBody Map<String, String> request) {
        return authenticationService.refresh(request);
    }

}