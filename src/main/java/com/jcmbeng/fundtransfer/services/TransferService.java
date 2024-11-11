package com.jcmbeng.fundtransfer.services;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.enums.TransferStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Service interface for handling operations related to fund transfers.
 */
public interface TransferService {

    /**
     * Creates and processes a fund transfer.
     *
     * @param dto {@link TransferDTO} containing details of the transfer, 
     *                       including sender, receiver, amount, and currency.
     * @return {@link TransferDTO} with details of the processed transfer, 
     *         including transfer status and any applied fees.
     */
    TransferDTO makeTransfer(TransferDTO dto, String rateExchangeProvider);

    /**
     * Finds a transfer by its unique reference identifier.
     *
     * @param reference Unique reference of the transfer.
     * @return {@link TransferDTO} with details of the identified transfer, or null if not found.
     */
    TransferDTO findByReference(String reference);

    /**
     * Retrieves a paginated and sorted list of transfers, with optional filters.
     * Allows filtering by account details, currency, transfer amount, date range, and status.
     *
     * @param fromAccount       ID of the source account as a string for filtering.
     * @param toAccount         ID of the target account as a string for filtering.
     * @param fromCurrency      Currency code of the source account, e.g., "USD".
     * @param toCurrency        Currency code of the target account, e.g., "EUR".
     * @param startDate         Start date for filtering transfers by timestamp.
     * @param endDate           End date for filtering transfers by timestamp.
     * @param minAmount         Minimum transfer amount for filtering.
     * @param maxAmount         Maximum transfer amount for filtering.
     * @param transferStatus    {@link TransferStatus} for filtering transfers by status.
     * @param paginationAndSortDTO {@link PaginationAndSortDTO} for pagination and sorting configuration.
     * @return {@link ResponsePaginatedAndSortedDTO} containing the paginated list of transfers
     *         and related pagination information.
     */
    ResponsePaginatedAndSortedDTO findAll(String fromAccount,
                                          String toAccount,
                                          String fromCurrency,
                                          String toCurrency,
                                          LocalDateTime startDate,
                                          LocalDateTime endDate,
                                          BigDecimal minAmount,
                                          BigDecimal maxAmount,
                                          TransferStatus transferStatus,
                                          PaginationAndSortDTO paginationAndSortDTO);
}
