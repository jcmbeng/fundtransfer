package com.jcmbeng.fundtransfer.mappers.impl;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.mappers.EntityMapper;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder

public class AccountMapper extends EntityMapper<Account, AccountDTO> {

    public AccountMapper(){}

    @Override
    public AccountDTO toDto (final Account entity) {
        if (entity == null) {
            return null; // or throw an exception
        }
        return AccountDTO.builder ()
                .id (entity.getId ())
                .accountNumber (entity.getAccountNumber ())
                .accountOwner (entity.getOwner ().getId ())
                .totalBalance (entity.getTotalBalance ())
                .usableBalance (entity.getUsableBalance ())
                .currency (entity.getCurrency ())
                .accountStatus (entity.getAccountStatus ())
                .creationDate (entity.getCreatedAt ()!=null ?  entity.getCreatedAt ().toString () : "")
                .lastUpdateDate (entity.getUpdatedAt ()!=null ?  entity.getUpdatedAt ().toString () : "")
                .build ();
    }

    @Override
    public Account toEntity (AccountDTO dto) {
        if (dto == null) {
            return null; // TODO throw an exception
        }
        return Account.builder ()
                .currency (dto.getCurrency ())
                .accountNumber (UUID.randomUUID ().toString ()) // This can be improved to generated real accounts
                // numbers
                .totalBalance (BigDecimal.ZERO)
                .usableBalance (BigDecimal.ZERO)
                .accountStatus (dto.getAccountStatus ())
                .build ();
    }

}
