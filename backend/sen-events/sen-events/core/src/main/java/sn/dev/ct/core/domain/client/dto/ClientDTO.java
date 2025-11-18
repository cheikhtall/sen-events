package sn.dev.ct.core.domain.client.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import sn.dev.ct.core.domain.account.entity.Account;

@Getter @Setter @Builder @AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private Long id;
    @NotBlank(message = "The first name cannot be empty")
    private String firstName;
    @NotBlank(message = "The last name cannot be empty")
    private String lastName;
    @NotBlank(message = "The phone number cannot be empty")
    private String phone;
    @NotBlank(message = "The email address cannot be empty")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$",
            message = "Invalide email format")
    private String email;
    private String address;
    private Long accountId;
}

