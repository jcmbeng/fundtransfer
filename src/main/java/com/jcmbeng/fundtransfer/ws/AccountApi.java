package com.jcmbeng.fundtransfer.ws;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Account API interface for account-related operations.
 * <p>
 * This interface extends {@link CustomWS} to provide standard CRUD operations on {@link AccountDTO}
 * while adding an endpoint to retrieve an account by its unique account number.
 * </p>
 */
@RequestMapping(
        value = "/api/v1/accounts"
)
public interface AccountApi extends CustomWS<AccountDTO> {

    /**
     * Retrieves account information based on the provided account number.
     *
     * @param accountNumber The unique account number to fetch account details.
     * @return A {@link ResponseEntity} with the account details or an appropriate error message.
     */
    @Operation(
            summary = "Retrieve account details by account number",
            description = "Fetches the account details for a specified account number if available."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account details"),
            @ApiResponse(responseCode = "404", description = "Account with the specified account number not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/account-number/{accountNumber}")
    ResponseEntity<?> getByAccountNumber(
            @Parameter(description = "Unique account number to identify the account", required = true)
            @PathVariable(name = "accountNumber") String accountNumber
    );
}

