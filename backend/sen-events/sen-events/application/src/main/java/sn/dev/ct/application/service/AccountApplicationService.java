package sn.dev.ct.application.service;

import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.dto.ChangePasswordDTO;

public interface AccountApplicationService {
    AccountDTO createAccount(AccountDTO accountDTO);
    void changePassword(Long id, ChangePasswordDTO changePasswordDTO);
}
