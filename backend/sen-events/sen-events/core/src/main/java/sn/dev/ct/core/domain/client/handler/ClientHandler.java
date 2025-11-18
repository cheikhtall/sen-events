package sn.dev.ct.core.domain.client.handler;

import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.entity.Client;
import sn.dev.ct.core.domain.client.mapper.ClientMapper;
import sn.dev.ct.core.domain.client.repository.ClientRepository;

import java.util.Optional;

@Component
public class ClientHandler {
    private final ClientRepository clientRepository;
    private final ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    public ClientHandler(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveOrUpdate(ClientDTO clientDTO) {
        Optional<Client> optionalClient = clientRepository.findById(clientDTO.getId());
        final Client client = optionalClient.orElse(Client.builder().build());
        mapper.updatedClientFromDto(clientDTO, client);
        return clientRepository.save(client);

        /*Client client;

        if (clientDTO.getId() == null) {
            // Nouveau client
            client = mapper.clientDTOToClient(clientDTO);
        } else {
            // Mise à jour
            client = clientRepository.findById(clientDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
            mapper.updatedClientFromDto(clientDTO, client);
        }

        return clientRepository.save(client);*/
    }
}
