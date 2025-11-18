package sn.dev.ct.core.domain.client.service;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.entity.Account;
import sn.dev.ct.core.domain.account.mapper.AccountMapper;
import sn.dev.ct.core.domain.account.repository.AccountRepository;
import sn.dev.ct.core.domain.account.service.AccountService;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.entity.Client;
import sn.dev.ct.core.domain.client.handler.ClientHandler;
import sn.dev.ct.core.domain.client.mapper.ClientMapper;
import sn.dev.ct.core.domain.client.repository.ClientRepository;
import sn.dev.ct.core.exceptions.ResourceNotFoundException;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);
    private final AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);
    private final ClientHandler clientHandler;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientHandler clientHandler,
                             AccountService accountService, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.clientHandler = clientHandler;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @Override
    public ClientDTO register(ClientDTO clientDTO, AccountDTO accountDTO) {

        Client client = mapper.clientDTOToClient(clientDTO);
        Account account = accountRepository.findById(accountDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account", accountDTO.getId()));
        client.setAccount(account);

        Client savedClient = clientRepository.save(client);

        return mapper.clientToClientDTO (savedClient);
    }

    @Override
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", clientDTO.getId()));
        mapper.updatedClientFromDto(clientDTO, client);
        clientRepository.save(client);
        return mapper.clientToClientDTO(client);
    }

    @Override
    public ClientDTO retrieves(Long id) {
        return clientRepository.findById(id).map(
                mapper::clientToClientDTO
        ).orElseThrow(() -> new ResourceNotFoundException("client",id));
    }

    @Override
    public Page<ClientDTO> retrieve(Pageable pageable) {
        return clientRepository.findAll(pageable).map(
                mapper::clientToClientDTO
        );
    }

    @Override
    public ClientDTO retrievesByEmail(String email) {
        return clientRepository.findByAccountEmail(email).map(
                mapper::clientToClientDTO
        ).orElseThrow(() -> new ResourceNotFoundException("client",email));
    }

    @Override
    public void delete(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));
        clientRepository.delete(client);
    }

    @Override
    public Page<ClientDTO> getAllClients(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ClientDTO> searchByName(String keyword, Pageable pageable) {
        return clientRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword, pageable)
                .map(mapper::clientToClientDTO);
    }
}
