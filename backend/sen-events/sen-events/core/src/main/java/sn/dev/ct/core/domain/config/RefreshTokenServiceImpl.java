package sn.dev.ct.core.domain.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.account.entity.Account;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(Account account) {
        refreshTokenRepository.findAll().stream()
                .filter(t -> t.getAccount().getId().equals(account.getId()))
                .forEach(t -> {
                    t.setRevoked(true);
                    refreshTokenRepository.save(t);
                });

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(60L * 60 * 24 * 30)) // 30 jours
                .account(account)
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken rt = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (rt.isRevoked())
            throw new RuntimeException("Refresh token revoked");

        if (rt.getExpiryDate().isBefore(Instant.now()))
            throw new RuntimeException("Refresh token expired");

        return rt;
    }

    @Override
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(rt -> {
            rt.setRevoked(true);
            refreshTokenRepository.save(rt);
        });
    }
}
