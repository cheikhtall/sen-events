package sn.dev.ct.application.security.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequest {
    @NotEmpty(message = "The username cannot be empty")
    private String username;
    @NotEmpty(message = "The password cannot be empty")
    private String password;
}
