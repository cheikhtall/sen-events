package sn.dev.ct.application.service;

import sn.dev.ct.core.domain.client.dto.ClientDTO;

public interface ClientApplicationService {
    ClientDTO register(ClientDTO clientDTO);
}
