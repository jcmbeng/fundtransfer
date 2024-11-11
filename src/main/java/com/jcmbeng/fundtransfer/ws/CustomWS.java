package com.jcmbeng.fundtransfer.ws;

import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.exceptions.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
/**
 * Interface for Custom Web Service (WS) endpoints.
 * Provides methods for creating, retrieving, updating, and deleting custom entities.
 *
 * @param <D> the type of the data transfer object (DTO) for the custom entity
 */
public interface CustomWS<D> {

    /**
     * Creates a new custom entity using the provided data transfer object (DTO).
     * This method handles HTTP POST requests for creating a new custom entity.
     *
     * @param dto the {@code D} containing the data for the new custom entity; must not be null
     * @return a {@code Response} object indicating the result of the create operation
     * @throws InvalidOperationException if the DTO is null
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> create(@RequestBody D dto);

    /**
     * Retrieves a custom entity with the specified ID.
     * This method handles HTTP GET requests to fetch a custom entity by its ID.
     *
     * @param id the ID of the custom entity to retrieve; must not be null
     * @return a {@code Response} object containing the custom entity data if found,
     * or an appropriate error response if not found
     * @throws InvalidOperationException if the ID is null
     */
    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> findById(@PathVariable(name = "id") Long id);

    /**
     * Updates the custom entity with the specified ID using the data from the provided DTO.
     * This method handles HTTP PUT requests for updating a custom entity.
     *
     * @param id  the ID of the custom entity to update; must not be null
     * @param dto the {@code D} containing the updated data; must not be null
     * @return a {@code Response} object indicating the result of the update operation
     * @throws InvalidOperationException if the ID or DTO is null
     * @throws ResourceNotFoundException if no custom entity with the specified ID is found
     */
    @PutMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody D dto);

    /**
     * Deletes the custom entity with the specified ID.
     * This method handles HTTP DELETE requests to remove a custom entity by its ID.
     *
     * @param id the ID of the custom entity to delete; must not be null
     * @return a {@code Response} object indicating the result of the delete operation
     * @throws InvalidOperationException if the ID is null
     * @throws ResourceNotFoundException if no custom entity with the specified ID is found
     */
    @DeleteMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id);

    /**
     * Retrieves a list of all custom entities.
     * This method handles HTTP GET requests to fetch all custom entities.
     *
     * @return a {@code Response} object containing the list of all custom entities,
     * or an appropriate error response if no custom entities are found
     */
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<?> findAll(@RequestParam(required = false, name ="page", defaultValue = "0") int page,
                              @RequestParam(required = false, name ="size", defaultValue = "10") int size,
                              @RequestParam(required = false, name ="sortBy", defaultValue = "id") String sortBy,
                              @RequestParam(required = false, name ="direction", defaultValue = "ASC") String direction);
}
