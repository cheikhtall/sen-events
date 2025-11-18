package sn.dev.ct.core.domain.config;

import jakarta.persistence.*;
import lombok.*;
import sn.dev.ct.core.domain.account.entity.Account;

import java.time.Instant;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 500)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private boolean revoked;
}
