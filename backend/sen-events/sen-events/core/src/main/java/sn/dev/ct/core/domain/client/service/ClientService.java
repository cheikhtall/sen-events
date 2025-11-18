package sn.dev.ct.core.domain.client.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.client.dto.ClientDTO;

public interface ClientService {
    ClientDTO register(ClientDTO clientDTO, AccountDTO accountDTO);
    ClientDTO update(Long id, ClientDTO clientDTO);
    ClientDTO retrieves (Long id);
    Page<ClientDTO> retrieve (Pageable pageable);
    ClientDTO retrievesByEmail(String email);
    void delete(Long id);
    Page<ClientDTO> getAllClients(Pageable pageable);
    Page<ClientDTO> searchByName(String keyword, Pageable pageable);
}
