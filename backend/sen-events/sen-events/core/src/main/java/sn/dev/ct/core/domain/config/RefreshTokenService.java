package sn.dev.ct.core.domain.config;

import sn.dev.ct.core.domain.account.entity.Account;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(Account account);
    RefreshToken validateRefreshToken(String token);
    void revokeToken(String token);
}
