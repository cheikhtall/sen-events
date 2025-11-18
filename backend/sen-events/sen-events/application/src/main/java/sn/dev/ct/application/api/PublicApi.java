package sn.dev.ct.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dev.ct.application.service.ClientApplicationService;
import sn.dev.ct.core.domain.client.dto.ClientDTO;

@RestController
@RequestMapping("/api/user")
public class PublicApi {

    private final ClientApplicationService clientApplicationService;
    public PublicApi(ClientApplicationService clientApplicationService) {
        this.clientApplicationService = clientApplicationService;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientDTO> register(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientApplicationService.register(clientDTO));
    }
}
