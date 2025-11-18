package sn.dev.ct.application.service;

import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.entity.AccountRole;
import sn.dev.ct.core.domain.account.entity.AccountStatus;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.service.ClientService;

@Service
public class ClientApplicationServiceImpl implements ClientApplicationService {

    private final ClientService clientService; // core
    private final AccountApplicationService accountApplicationService;

    public ClientApplicationServiceImpl(ClientService clientService, AccountApplicationService accountApplicationService) {
        this.clientService = clientService;
        this.accountApplicationService = accountApplicationService;
    }
    @Override
    public ClientDTO register(ClientDTO clientDTO) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(clientDTO.getEmail());
        accountDTO.setUsername(clientDTO.getEmail());
        accountDTO.setRole(AccountRole.CLIENT.name());
        accountDTO.setStatus(AccountStatus.ACTIVE.name());

        AccountDTO savedAccount = accountApplicationService.createAccount(accountDTO);
        return clientService.register(clientDTO, savedAccount);
    }
}
