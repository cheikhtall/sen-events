package sn.dev.ct.infra.customevent.event;

import lombok.Getter;
import lombok.Setter;
import sn.dev.ct.core.domain.account.dto.AccountDTO;

@Getter @Setter
public class AccountCreatedEvent {
    private final AccountDTO account;
    private final String rawPassword;

    public AccountCreatedEvent(AccountDTO account, String rawPassword) {
        this.account = account;
        this.rawPassword = rawPassword;
    }
}
