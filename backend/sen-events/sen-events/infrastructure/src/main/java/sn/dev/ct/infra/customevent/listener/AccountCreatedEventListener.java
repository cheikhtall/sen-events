package sn.dev.ct.infra.customevent.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.infra.customevent.event.AccountCreatedEvent;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.infra.mail.EmailService;

@Component
public class AccountCreatedEventListener {
    private final EmailService emailService;

    public AccountCreatedEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @Async
    @EventListener
    public void handleUserCreated(AccountCreatedEvent event) {
        AccountDTO account = event.getAccount();
        emailService.sendWelcomeEmail(account, event.getRawPassword());
    }
}
