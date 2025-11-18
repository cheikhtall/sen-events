package sn.dev.ct.core.domain.client.mapper;

import org.mapstruct.*;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.entity.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.email", target = "email")
    ClientDTO clientToClientDTO(Client client);
    @Mapping(source = "accountId", target = "account.id")
    @Mapping(source = "email", target = "account.email")
    Client clientDTOToClient(ClientDTO clientDTO);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedClientFromDto(ClientDTO clientDTO, @MappingTarget Client client);
}
