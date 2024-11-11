package com.jcmbeng.fundtransfer.ws.controllers;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.handlers.ResponseHandler;
import com.jcmbeng.fundtransfer.services.ClientService;
import com.jcmbeng.fundtransfer.ws.ClientApi;
import io.swagger.v3.oas.annotations.Operation;
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
public class ClientController implements ClientApi {
    private final ClientService clientService;
    /**
     * {@inheritDoc}
     */

    @Operation(
            summary = "Create a new Client",
            description = "Create a new Client object and return the specified DTO. The response is ClientDTO"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(implementation = ClientDTO.class)
                    , mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", content = {@Content(mediaType = "application/json")})})

    @Override
    public ResponseEntity<?> create (ClientDTO dto) {
        try {
            return ResponseHandler.generateResponse(clientService.create(dto), RESOURCE_CREATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    @Override
    public ResponseEntity<?> findById (Long id) {
        try {
            return ResponseHandler.generateResponse(clientService.findById(id), RESOURCE_FOUND_WITH_ID);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    @Override
    public ResponseEntity<?> update (Long id, ClientDTO dto) {
        try {

            return ResponseHandler.generateResponse(clientService.update(id, dto), RESOURCE_UPDATED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    @Override
    public ResponseEntity<?> deleteById (Long id) {
        try {
            return ResponseHandler.generateResponse(clientService.delete(id), RESOURCE_DELETED);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException(exception);
        }
    }

    @Override
    public ResponseEntity<?> findAll (int page, int size, String sortBy, String direction) {
        try {
            PaginationAndSortDTO paginationAndSortDTO = PaginationAndSortDTO.getPaginAndSort (page, size,sortBy,direction);
            return ResponseHandler.response (clientService.findAll (paginationAndSortDTO),
                    SUCCESSFUL);
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }
}
