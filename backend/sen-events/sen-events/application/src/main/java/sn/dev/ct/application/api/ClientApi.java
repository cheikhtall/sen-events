package sn.dev.ct.application.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sn.dev.ct.application.security.model.AccountUserDetails;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientApi {

    private final ClientService clientService;

    public ClientApi(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/account-infos")
    public ClientDTO getMyAccount(@AuthenticationPrincipal AccountUserDetails userDetails) {
        return clientService.retrievesByEmail(userDetails.getAccount().getEmail());
    }

    @PutMapping("/client/{id}")
    public ClientDTO updateClient(@AuthenticationPrincipal AccountUserDetails userDetails,
                                  @PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.update(id, clientDTO);
    }
}
