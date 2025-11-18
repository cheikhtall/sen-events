package sn.dev.ct.core.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Long id;
    @NotBlank(message = "The username cannot be empty")
    private String username;
    @NotBlank(message = "The email cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$",
            message = "Invalide email format")
    private String email;
    private String role;
    private String status;
}
