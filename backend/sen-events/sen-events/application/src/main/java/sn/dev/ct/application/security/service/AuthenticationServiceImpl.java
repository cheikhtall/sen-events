package sn.dev.ct.application.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.dev.ct.application.security.dto.JwtResponse;
import sn.dev.ct.application.security.dto.SignInRequest;
import sn.dev.ct.application.security.model.AccountUserDetails;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.account.repository.AccountRepository;
import sn.dev.ct.core.domain.config.RefreshToken;
import sn.dev.ct.core.domain.config.RefreshTokenService;

import java.util.Map;

@Service @RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AccountRepository accountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public JwtResponse authenticate(SignInRequest signInRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword())
        );
        var user = accountRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new AuthenticationException("User with username " + signInRequest.getUsername() + " not found") {
                });

        if(!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        if (!user.isEnabled()){
            throw new BadCredentialsException("This account is disabled");
        }
        AccountUserDetails userDetails = new AccountUserDetails(user);
        var token = jwtService.generateToken(userDetails);
        var refreshToken = refreshTokenService.createRefreshToken(user);

        return JwtResponse.builder()
                .id(user.getId())
                .token(token)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .enabled(user.isEnabled())
                .type("Bearer")
                .build();
    }

    @Override
    public JwtResponse refresh(Map<String, String> request) {
        String refreshToken = request.get("refreshToken");

        RefreshToken rt = refreshTokenService.validateRefreshToken(refreshToken);
        Account account = rt.getAccount();

        // Rotation : on invalide l'ancien et on crée un nouveau
        refreshTokenService.revokeToken(refreshToken);
        RefreshToken newRt = refreshTokenService.createRefreshToken(account);

        String newAccessToken = jwtService.generateToken(
                new AccountUserDetails(account)
        );

        return JwtResponse.builder()
                .id(account.getId())
                .token(newAccessToken)
                .refreshToken(newRt.getToken())
                .username(account.getUsername())
                .email(account.getEmail())
                .role(account.getRole())
                .enabled(account.isEnabled())
                .type("Bearer")
                .build();
    }

    @Override
    public void logout(Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        refreshTokenService.revokeToken(refreshToken);
    }
}
