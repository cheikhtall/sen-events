package sn.dev.ct.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.account.entity.AccountRole;
import sn.dev.ct.core.domain.account.entity.AccountStatus;
import sn.dev.ct.core.domain.account.repository.AccountRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    @Override
    public void run(String... args) {
        String adminEmail = "admin@email.com";
        String adminPassword = "admin";
        if (accountRepository.findByEmail(adminEmail).isEmpty()) {
            Account admin = new Account();
            admin.setEmail(adminEmail);
            admin.setPassword(adminPassword);
            admin.setUsername(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(AccountRole.ADMIN);
            admin.setStatus(AccountStatus.ACTIVE);
            admin.setEnabled(true);

            accountRepository.save(admin);
            System.out.println("Admin user created with email: " + adminEmail);
        }
    }
}
