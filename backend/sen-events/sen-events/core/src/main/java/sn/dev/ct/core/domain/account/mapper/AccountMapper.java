package sn.dev.ct.core.domain.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.entity.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toAccount(AccountDTO accountDTO);
    AccountDTO toAccountDTO(Account account);
}
