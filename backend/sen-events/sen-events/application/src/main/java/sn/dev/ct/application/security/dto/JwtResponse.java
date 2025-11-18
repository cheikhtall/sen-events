package sn.dev.ct.application.security.dto;

import lombok.*;
import sn.dev.ct.core.domain.account.entity.AccountRole;

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @Builder
public class JwtResponse {
    private String token;
    private String refreshToken;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    boolean enabled;
    private AccountRole role;

    public JwtResponse(String token, String refreshToken, Long id, String username, String email,
                       boolean enabled, AccountRole role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.role = role;
    }
}
