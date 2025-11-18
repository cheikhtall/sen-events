package sn.dev.ct.core.domain.account.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO {
    @NotBlank(message = "The old password cannot be empty")
    private String oldPassword;
    @NotBlank(message = "The new password cannot be empty")
    private String newPassword;
    @NotBlank(message = "The confirm password cannot be empty")
    private String confirmPassword;
}
