package com.jcmbeng.fundtransfer.mappers.impl;

import com.jcmbeng.fundtransfer.dtos.TransactionDTO;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.mappers.EntityMapper;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public class TransactionMapper extends EntityMapper<Transaction, TransactionDTO> {

    public TransactionMapper(){}

    @Override
    public TransactionDTO toDto (final Transaction entity) {
        if (entity == null) {
            return null; // or throw an exception
        }
        return TransactionDTO.builder ()
                .id (entity.getId ())
                .accountNumber(entity.getAccount ().getAccountNumber ())
                .transactionReference (entity.getTransactionReference ())
                .status (entity.getStatus ())
                .currency (entity.getAccount ().getCurrency ())
                .description (entity.getDescription ())
                .amount (entity.getAmount ())
                .method (entity.getMethod())
                .transactionType(entity.getType ())
                .creationDate (entity.getCreatedAt ()!=null ?  entity.getCreatedAt ().toString () : "")
                .build ();
    }

    @Override
    public Transaction toEntity (TransactionDTO dto) {
        if (dto == null) {
            return null; // TODO throw an exception
        }
        return Transaction.builder ()
                .description (dto.getDescription ())
                .amount (dto.getAmount ())
                .method (dto.getMethod())
                .status (dto.getStatus ())
                .build ();
    }

}
