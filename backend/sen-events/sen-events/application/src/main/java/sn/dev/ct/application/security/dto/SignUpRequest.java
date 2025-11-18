package sn.dev.ct.application.security.dto;

import jakarta.validation.constraints.NotEmpty;

public class SignUpRequest {
    @NotEmpty(message = "The username cannot be empty")
    private String username;
    @NotEmpty(message = "The password cannot be empty")
    private String password;
}
