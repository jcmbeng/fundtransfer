package com.jcmbeng.fundtransfer.ws.controllers;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.handlers.ResponseHandler;
import com.jcmbeng.fundtransfer.services.AccountService;
import com.jcmbeng.fundtransfer.ws.AccountApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements AccountApi {

    private final AccountService clientService;

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Create a new account", description = "Creates a new account with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid account data provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> create(AccountDTO dto) {
        try {
            return ResponseHandler.generateResponse(clientService.create(dto), RESOURCE_CREATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Find account by ID", description = "Retrieves account details based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "Account not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> findById(
            @Parameter(description = "ID of the account to be retrieved", required = true)
            Long id
    ) {
        try {
            return ResponseHandler.generateResponse(clientService.findById(id), RESOURCE_FOUND_WITH_ID);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Get account by account number", description = "Fetches account details for a specified account number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account found"),
            @ApiResponse(responseCode = "404", description = "No account found with the specified account number"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> getByAccountNumber(
            @Parameter(description = "Unique account number to identify the account", required = true)
            String accountNumber
    ) {
        try {
            return ResponseHandler.generateResponse(clientService.getByAccountNumber(accountNumber), RESOURCE_FOUND_WITH_ID);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Update account details", description = "Updates the account details for the specified account ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found with the provided ID"),
            @ApiResponse(responseCode = "400", description = "Invalid update details provided"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> update(
            @Parameter(description = "ID of the account to update", required = true)
            Long id,
            AccountDTO dto
    ) {
        try {
            return ResponseHandler.generateResponse(clientService.update(id, dto), RESOURCE_UPDATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Delete account by ID", description = "Deletes the account associated with the specified ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found with the provided ID"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> deleteById(
            @Parameter(description = "ID of the account to delete", required = true)
            Long id
    ) {
        try {
            return ResponseHandler.generateResponse(clientService.delete(id), RESOURCE_DELETED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Operation(summary = "Get all accounts", description = "Retrieves all accounts with optional pagination and sorting parameters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Accounts retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Override
    public ResponseEntity<?> findAll(
            @Parameter(description = "Page number for pagination", example = "0") int page,
            @Parameter(description = "Size of the page for pagination", example = "10") int size,
            @Parameter(description = "Field by which to sort the results", example = "id") String sortBy,
            @Parameter(description = "Direction of sorting", example = "asc") String direction
    ) {
        try {
            PaginationAndSortDTO paginationAndSortDTO = PaginationAndSortDTO.getPaginAndSort(page, size, sortBy, direction);
            return ResponseHandler.response(clientService.findAll(paginationAndSortDTO), SUCCESSFUL);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }
}
