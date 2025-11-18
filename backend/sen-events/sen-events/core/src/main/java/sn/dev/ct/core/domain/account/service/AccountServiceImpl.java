package sn.dev.ct.core.domain.account.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.account.entity.AccountStatus;
import sn.dev.ct.core.domain.account.mapper.AccountMapper;
import sn.dev.ct.core.domain.account.repository.AccountRepository;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;


@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO, String rawPassword) {
        if (accountRepository.existsByEmail(accountDTO.getEmail())) {
            throw new IllegalArgumentException(
                    "Un compte avec l'email %s existe déjà " +accountDTO.getEmail()
            );
        }
        Account account = mapper.toAccount(accountDTO);
        account.setPassword(rawPassword);
        account.setStatus(AccountStatus.ACTIVE);
        account.setup();
        Account savedAccount = accountRepository.save(account);
        return mapper.toAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", id));
        return mapper.toAccountDTO(account);
    }

    @Override
    public AccountDTO getAccountByUsername(String username) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Account", username));
        account.setup();
        return mapper.toAccountDTO(account);
    }

    @Override
    public void activateAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", id));
        account.setStatus(AccountStatus.ACTIVE);
        account.setEnabled(true);
        accountRepository.save(account);
    }

    @Override
    public void desactivateAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", id));
        account.setStatus(AccountStatus.INACTIVE);
        account.setEnabled(false);
        accountRepository.save(account);
    }

    @Override
    public void suspendAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account", id));
        account.setStatus(AccountStatus.SUSPENDED);
        account.setEnabled(false);
        accountRepository.save(account);
    }

    @Override
    public Page<AccountDTO> getAllAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable).map(
                mapper::toAccountDTO
        );
    }
}
