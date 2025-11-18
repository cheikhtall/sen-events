package sn.dev.ct.core.domain.account.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.dev.ct.core.domain.account.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO, String rawPassword);
    AccountDTO getAccountById(Long id);
    AccountDTO getAccountByUsername(String username);
    void activateAccount(Long id);
    void desactivateAccount(Long id);
    void suspendAccount(Long id);
    Page<AccountDTO> getAllAccounts(Pageable pageable);
}
