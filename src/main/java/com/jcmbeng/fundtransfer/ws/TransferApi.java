package com.jcmbeng.fundtransfer.ws;

import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequestMapping(
        value = "/api/v1/transfers"
)
public interface TransferApi {

    /**
     * Initiates a transfer between two accounts.
     *
     * @param transferDTO The transfer details to be processed.
     * @return A ResponseEntity indicating the result of the transfer operation.
     */
    @Operation(
            summary = "Initiate a transfer",
            description = "Creates a new transfer transaction between accounts."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transfer successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid transfer data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    ResponseEntity<?> transfer(
            @Parameter(description = "Transfer details including from and to accounts, currency, and amount", required = true)
            @RequestBody TransferDTO transferDTO
    );

    /**
     * Retrieves transfer details by transfer ID.
     *
     * @param id The unique ID of the transfer.
     * @return A ResponseEntity containing the transfer details or an error response.
     */
    @Operation(
            summary = "Retrieve transfer by ID",
            description = "Fetches the transfer details based on the provided transfer ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer found"),
            @ApiResponse(responseCode = "404", description = "Transfer not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    ResponseEntity<?> getById(
            @Parameter(description = "ID of the transfer to retrieve", required = true)
            @PathVariable(name = "id") Long id
    );

    /**
     * Retrieves a transfer by its reference number.
     *
     * @param reference The unique reference number of the transfer.
     * @return A ResponseEntity containing the transfer details or an error response.
     */
    @Operation(
            summary = "Retrieve transfer by reference",
            description = "Fetches the transfer details based on the provided transfer reference."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfer found"),
            @ApiResponse(responseCode = "404", description = "Transfer not found with the provided reference"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("transfer-reference/{reference}")
    ResponseEntity<?> getByTransferReference(
            @Parameter(description = "Reference number of the transfer to retrieve", required = true)
            @PathVariable(name = "reference") String reference
    );

    /**
     * Retrieves a list of transfers with optional filters, pagination, and sorting.
     *
     * @param fromAccount   The account from which the transfer originated (optional).
     * @param toAccount     The account to which the transfer was made (optional).
     * @param fromCurrency  The currency the transfer was made in (optional).
     * @param toCurrency    The currency of the receiving account (optional).
     * @param transferStatus The status of the transfer (optional).
     * @param startDate     The start date range for the transfer (optional).
     * @param endDate       The end date range for the transfer (optional).
     * @param minAmount     The minimum transfer amount (optional).
     * @param maxAmount     The maximum transfer amount (optional).
     * @param page          The page number for pagination (optional, default is 0).
     * @param size          The number of results per page (optional, default is 10).
     * @param sortBy        The field to sort the results by (optional, default is "id").
     * @param direction     The sort direction (optional, default is "ASC").
     * @return A ResponseEntity containing the list of transfers or an error response.
     */
    @Operation(
            summary = "Retrieve transfers with filters, pagination, and sorting",
            description = "Fetches a list of transfers with optional filtering by account, currency, status, date range, and amount. Includes pagination and sorting."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transfers found and returned"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    ResponseEntity<?> findAllTransfers(
            @Parameter(description = "Account from which the transfer originated", required = false)
            @RequestParam(required = false, name = "fromAccount") String fromAccount,

            @Parameter(description = "Account to which the transfer was made", required = false)
            @RequestParam(required = false, name = "toAccount") String toAccount,

            @Parameter(description = "Currency of the sending account", required = false)
            @RequestParam(required = false, name = "fromCurrency") String fromCurrency,

            @Parameter(description = "Currency of the receiving account", required = false)
            @RequestParam(required = false, name = "toCurrency") String toCurrency,

            @Parameter(description = "Status of the transfer", required = false)
            @RequestParam(required = false, name = "transferStatus") TransferStatus transferStatus,

            @Parameter(description = "Start date for filtering transfers", required = false)
            @RequestParam(required = false, name = "startDate") LocalDateTime startDate,

            @Parameter(description = "End date for filtering transfers", required = false)
            @RequestParam(required = false, name = "endDate") LocalDateTime endDate,

            @Parameter(description = "Minimum transfer amount for filtering", required = false)
            @RequestParam(required = false, name = "minAmount") BigDecimal minAmount,

            @Parameter(description = "Maximum transfer amount for filtering", required = false)
            @RequestParam(required = false, name = "maxAmount") BigDecimal maxAmount,

            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(required = false, name = "page", defaultValue = "0") int page,

            @Parameter(description = "Size of the page for pagination", example = "10")
            @RequestParam(required = false, name = "size", defaultValue = "10") int size,

            @Parameter(description = "Field by which to sort the results", example = "id")
            @RequestParam(required = false, name = "sortBy", defaultValue = "id") String sortBy,

            @Parameter(description = "Sort direction", example = "ASC")
            @RequestParam(required = false, name = "direction", defaultValue = "ASC") String direction
    );
}

