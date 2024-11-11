package com.jcmbeng.fundtransfer.services;

import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import jakarta.transaction.Transactional;

public interface GenericService<D> {

    /**
     * Create a new entity of type D.
     *
     * @param dto Data transfer object of type D representing the entity to be created.
     * @return The created entity of type D.
     */
    @Transactional
    D create(D dto);

    /**
     * Retrieve all entities of type D.
     *
     * @return A list of all entities of type D.
     */
    @Transactional
    ResponsePaginatedAndSortedDTO findAll (PaginationAndSortDTO paginationAndSortDTO);

    /**
     * Find an entity of type D by its ID.
     *
     * @param id The ID of the entity to be found.
     * @return The found entity of type D, or null if no entity with the given ID exists.
     */
    @Transactional
    D findById(Long id);

    /**
     * Update an existing entity of type D.
     *
     * @param id The ID of the entity to be updated.
     * @param dto Data transfer object of type D containing updated data.
     * @return The updated entity of type D.
     */
    @Transactional
    D update(Long id, D dto);

    /**
     * Delete an entity of type D by its ID.
     *
     * @param id The ID of the entity to be deleted.
     * @return A message indicating the result of the deletion operation.
     */
    @Transactional
    String delete(Long id);
}
