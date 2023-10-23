package com.devsu.account.service.account;

import com.devsu.account.dto.account.*;
import com.devsu.account.model.Account;
import com.devsu.account.service.ICrudMapper;
import org.mapstruct.*;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountMapper extends ICrudMapper<AccountDTO, Account> {

    AccountItemResponse toItemResponse(AccountDTO accountDTO);

    @Mapping(target = "clientId", source = "client.id")
    AccountDetailResponse toDetailResponse(AccountDTO one);

    @Override
    @Mapping(target = "client.id", source = "clientId")
    AccountDTO toDTO(Account account);

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "client.id", target = "clientId")
    Account toEntity(AccountDTO accountDTO);

    @Mapping(target = "client.id", source = "clientId")
    AccountDTO toDTO(AccountCreateRequest data);

    AccountDTO toDTO(AccountUpdateRequest data);

    @Mapping(target = "id", ignore = true)
    AccountDTO updateDTO(@MappingTarget AccountDTO account, ClientDto client);
}
