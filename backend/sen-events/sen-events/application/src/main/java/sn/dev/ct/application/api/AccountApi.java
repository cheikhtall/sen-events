package sn.dev.ct.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sn.dev.ct.application.security.model.AccountUserDetails;
import sn.dev.ct.application.security.service.AuthenticationService;
import sn.dev.ct.application.service.AccountApplicationService;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.dto.ChangePasswordDTO;
import sn.dev.ct.core.domain.account.service.AccountService;
import sn.dev.ct.core.domain.client.service.ClientService;

import java.util.Map;

@RestController
@RequestMapping("/api/account/")
public class AccountApi {
    private final AccountService accountService;
    private final AccountApplicationService accountApplicationService;
    private final AuthenticationService authenticationService;
    private final ClientService clientService;

    public AccountApi(AccountService accountService, AccountApplicationService accountApplicationService,
                      AuthenticationService authenticationService, ClientService clientService) {
        this.accountService = accountService;
        this.accountApplicationService = accountApplicationService;
        this.authenticationService = authenticationService;
        this.clientService = clientService;
    }

    @GetMapping("/me")
    public AccountDTO getMyAccount(@AuthenticationPrincipal AccountUserDetails userDetails) {
        return accountService.getAccountById(userDetails.getAccount().getId());
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal AccountUserDetails userDetails,
                                                 @RequestBody ChangePasswordDTO changePasswordDTO) {
        accountApplicationService.changePassword(userDetails.getAccount().getId(), changePasswordDTO);
        return ResponseEntity.ok("Password changed");
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateAccount(@AuthenticationPrincipal AccountUserDetails userDetails) {
        accountService.activateAccount(userDetails.getAccount().getId());
        return ResponseEntity.ok("Account successfully activated");
    }

    @PostMapping("/desactivate")
    public ResponseEntity<String> desactivateAccount(@AuthenticationPrincipal AccountUserDetails userDetails) {
        accountService.desactivateAccount(userDetails.getAccount().getId());
        return ResponseEntity.ok("Account successfully deactivated");
    }

    @PostMapping("/logout")
    public void logout(@RequestBody Map<String, String> request) {
        authenticationService.logout(request);
    }

}
