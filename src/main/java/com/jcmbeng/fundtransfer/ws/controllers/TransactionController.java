package com.jcmbeng.fundtransfer.ws.controllers;

import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.TransactionDTO;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.handlers.ResponseHandler;
import com.jcmbeng.fundtransfer.services.TransactionService;
import com.jcmbeng.fundtransfer.ws.TransactionApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.*;

@RestController
@RequiredArgsConstructor
public class TransactionController implements TransactionApi {

    private final TransactionService transactionService;

    /**
     * Credit an account with a transaction.
     *
     * @param transactionDto The transaction details for the credit.
     * @return A ResponseEntity indicating the result of the credit operation.
     */
    @Operation(
            summary = "Credit an account",
            description = "Credits the specified account with the provided transaction details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> credit(
            @Parameter(description = "Transaction details for credit operation", required = true)
            TransactionDTO transactionDto
    ) {
        try {
            return ResponseHandler.generateResponse(transactionService.credit(transactionDto), RESOURCE_CREATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * Debit an account with a transaction.
     *
     * @param transactionDto The transaction details for the debit.
     * @return A ResponseEntity indicating the result of the debit operation.
     */
    @Operation(
            summary = "Debit an account",
            description = "Debits the specified account with the provided transaction details."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid transaction data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> debit(
            @Parameter(description = "Transaction details for debit operation", required = true)
            TransactionDTO transactionDto
    ) {
        try {
            return ResponseHandler.generateResponse(transactionService.debit(transactionDto), RESOURCE_CREATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * Retrieve transaction details by transaction ID.
     *
     * @param id The unique ID of the transaction.
     * @return A ResponseEntity containing the transaction details or an error response.
     */
    @Operation(
            summary = "Retrieve transaction by ID",
            description = "Fetches the transaction details based on the provided transaction ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> getById(
            @Parameter(description = "ID of the transaction to retrieve", required = true)
            Long id
    ) {
        try {
            return ResponseHandler.generateResponse(transactionService.findById(id), RESOURCE_FOUND_WITH_ID);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * Retrieve transaction details by transaction reference number.
     *
     * @param reference The unique reference number of the transaction.
     * @return A ResponseEntity containing the transaction details or an error response.
     */
    @Operation(
            summary = "Retrieve transaction by reference",
            description = "Fetches the transaction details based on the provided transaction reference."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction found"),
            @ApiResponse(responseCode = "404", description = "Transaction not found with the provided reference"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> getByTransactionReference(
            @Parameter(description = "Transaction reference number to retrieve", required = true)
            String reference
    ) {
        try {
            return ResponseHandler.generateResponse(transactionService.findByReference(reference), SUCCESSFUL);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * Retrieves a list of transactions with optional filters, pagination, and sorting.
     *
     * @param transactionType   Type of the transaction (optional).
     * @param transactionStatus Status of the transaction (optional).
     * @param transactionMethod Method of the transaction (optional).
     * @param accountNumber     Account number associated with the transaction (optional).
     * @param transactionDate   Date of the transaction (optional).
     * @param page              Page number for pagination (optional).
     * @param size              Size of the page for pagination (optional).
     * @param sortBy            Field by which to sort the results (optional).
     * @param direction         Direction of sorting (optional).
     * @return A ResponseEntity containing the list of transactions or an error response.
     */
    @Operation(
            summary = "Retrieve transactions with filters, pagination, and sorting",
            description = "Fetches a list of transactions with optional filtering by transaction type, status, method, account number, and date. Includes pagination and sorting."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> findAllTransactions(
            @Parameter(description = "Type of transaction", required = false)
            TransactionType transactionType,

            @Parameter(description = "Status of the transaction", required = false)
            TransactionStatus transactionStatus,

            @Parameter(description = "Method used for the transaction", required = false)
            TransactionMethod transactionMethod,

            @Parameter(description = "Account number associated with the transaction", required = false)
            String accountNumber,

            @Parameter(description = "Date of the transaction", required = false)
            String transactionDate,

            @Parameter(description = "Page number for pagination", example = "0")
            int page,

            @Parameter(description = "Size of the page for pagination", example = "10")
            int size,

            @Parameter(description = "Field to sort the results by", example = "id")
            String sortBy,

            @Parameter(description = "Direction to sort the results", example = "ASC")
            String direction
    ) {
        try {
            PaginationAndSortDTO paginationAndSortDTO = PaginationAndSortDTO.getPaginAndSort(page, size, sortBy, direction);
            return ResponseHandler.response(
                    transactionService.findAll(
                            transactionType,
                            transactionStatus,
                            transactionMethod,
                            accountNumber,
                            transactionDate,
                            paginationAndSortDTO
                    ),
                    SUCCESSFUL
            );
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }
}
