package sn.dev.ct.application.security.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sn.dev.ct.application.security.model.AccountUserDetails;
import sn.dev.ct.core.domain.account.repository.AccountRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final AccountRepository accountRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username){
                return accountRepository.findByUsername(username)
                        .map(AccountUserDetails::new)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found" +username));
            }
        };
    }
}
