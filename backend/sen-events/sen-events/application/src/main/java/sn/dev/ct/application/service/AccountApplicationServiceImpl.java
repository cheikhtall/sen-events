package sn.dev.ct.application.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.dto.ChangePasswordDTO;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.account.repository.AccountRepository;
import sn.dev.ct.core.domain.account.service.AccountService;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;
import sn.dev.ct.core.utils.StringUtils;
import sn.dev.ct.infra.customevent.event.AccountCreatedEvent;

import java.util.Objects;

@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccountApplicationServiceImpl(PasswordEncoder passwordEncoder, AccountService accountService,
                                         AccountRepository accountRepository,ApplicationEventPublisher eventPublisher) {
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
    }
    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        String rawPassword = StringUtils.generateRandomString(12);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        AccountDTO savedAccount = accountService.createAccount(accountDTO, encodedPassword);
        System.out.println("Mot de passe généré (raw) : " + rawPassword);
        System.out.println("Le mail :: " + accountDTO.getEmail());
        eventPublisher.publishEvent(new AccountCreatedEvent(savedAccount, rawPassword));

        return savedAccount;
    }

    @Override
    public void changePassword(Long id, ChangePasswordDTO changePasswordDTO) {
        Objects.requireNonNull(id);

        Account optionalAccount = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", id));

         if(!passwordEncoder.matches(changePasswordDTO.getOldPassword(),optionalAccount.getPassword())){
         throw new IllegalArgumentException("Le mot de passe saisi n'est pas votre mot de passe actuel");
         }

        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())){
            throw new IllegalArgumentException("Le nouveau mot de passe et la confirmation ne correspondent pas");
        }
        optionalAccount.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        accountRepository.save(optionalAccount);
    }
}
