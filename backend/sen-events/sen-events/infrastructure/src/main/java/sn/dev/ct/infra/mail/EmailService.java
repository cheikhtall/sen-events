package sn.dev.ct.infra.mail;

import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.entity.Account;

public interface EmailService {
    void sendHtmlEmail(EmailRequestDTO emailRequestDTO);
    void sendWelcomeEmail(AccountDTO account, String rawPassword);
}
