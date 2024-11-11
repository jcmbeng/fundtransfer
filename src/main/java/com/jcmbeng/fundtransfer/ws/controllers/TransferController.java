package com.jcmbeng.fundtransfer.ws.controllers;

import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.enums.ExchangeRateProvider;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.handlers.ResponseHandler;
import com.jcmbeng.fundtransfer.services.TransferService;
import com.jcmbeng.fundtransfer.ws.TransferApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.RESOURCE_CREATED;
import static com.jcmbeng.fundtransfer.enums.CustomMessage.SUCCESSFUL;

@RestController
public class TransferController implements TransferApi {

   private final  TransferService transferService;

    public TransferController (TransferService transferService) {
        this.transferService = transferService;
    }

    @Override
    public ResponseEntity<?> transfer (TransferDTO dto) {
        try {
            return ResponseHandler.generateResponse(transferService.makeTransfer (dto, ExchangeRateProvider.FREE_CURRENCY_API),
                    RESOURCE_CREATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    @Override
    public ResponseEntity<?> getById (Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> getByTransferReference (String reference) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAllTransfers (String fromAccount,
                                               String toAccount,
                                               String fromCurrency,
                                               String toCurrency,
                                               TransferStatus transferStatus,
                                               LocalDateTime startDate,
                                               LocalDateTime endDate,
                                               BigDecimal minAmount,
                                               BigDecimal maxAmount,
                                               int page,
                                               int size,
                                               String sortBy,
                                               String direction) {
        PaginationAndSortDTO paginationAndSortDTO = PaginationAndSortDTO.getPaginAndSort (page, size,sortBy,direction);
        return ResponseHandler.response (this.transferService
                        .findAll(
                                fromAccount,
                                toAccount,
                                fromCurrency,
                                toCurrency,
                                startDate,
                                endDate,
                                minAmount,
                                maxAmount,
                                transferStatus,
                                paginationAndSortDTO),
                SUCCESSFUL);
    }


}
