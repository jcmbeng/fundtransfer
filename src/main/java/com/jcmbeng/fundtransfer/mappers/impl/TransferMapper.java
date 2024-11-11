package com.jcmbeng.fundtransfer.mappers.impl;

import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.entities.Transfer;
import com.jcmbeng.fundtransfer.entities.Transfer;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import com.jcmbeng.fundtransfer.exceptions.InvalidEntityException;
import com.jcmbeng.fundtransfer.mappers.EntityMapper;
import com.jcmbeng.fundtransfer.utils.StringUtil;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.CONTENT_DATA_INVALID;

@Builder
public class TransferMapper extends EntityMapper<Transfer, TransferDTO> {

    public TransferMapper (){}

    @Override
    public TransferDTO toDto (final Transfer entity) {
        if (entity == null) {
            return null; // or throw an exception
        }
        return TransferDTO.builder ()
                .id (entity.getId ())
                .transferReference (StringUtil.referenceGenerator ("TR"))
                .amountSent (entity.getAmountSent ())
                .amountReceived (entity.getAmountReceived ())
                .exchangeRate (entity.getExchangeRate ())
                .description (entity.getDescription ())
                .fromCurrency (entity.getFromAccount ().getCurrency ())
                .toCurrency (entity.getToAccount ().getCurrency ())
                .fromAccount (entity.getFromAccount ().getAccountNumber ())
                .toAccount (entity.getToAccount ().getAccountNumber ())
                .creditReference (entity.getCreditReference ().getTransactionReference ())
                .debitReference (entity.getDebitReference ().getTransactionReference ())
                .transferStatus (TransferStatus.COMPLETED)
                .creationDate (entity.getCreatedAt ().toString ())
                .transactionDate(entity.getTimestamp ().toString ())
                .build ();
    }

    @Override
    public Transfer toEntity (TransferDTO dto) {
        if (dto == null) {
            throw new InvalidEntityException (CONTENT_DATA_INVALID);
        }
        return Transfer.builder ()
                .transferReference (StringUtil.referenceGenerator ("TR"))
                .fee (  BigDecimal.ZERO ) // TODO Implement a proper fee method depending th type of ransaction
                .description (dto.getDescription ())
                .amountSent (dto.getAmountSent ())
                .description (dto.getDescription ())
                .timestamp (LocalDateTime.now() )
                .build ();
    }

}
