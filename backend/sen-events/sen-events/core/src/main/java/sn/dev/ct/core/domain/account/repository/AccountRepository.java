package sn.dev.ct.core.domain.account.repository;

import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.base.BaseRepository;

import java.util.Optional;

public interface AccountRepository extends BaseRepository<Account> {
    boolean existsByEmail(String email);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsername(String username);
}
