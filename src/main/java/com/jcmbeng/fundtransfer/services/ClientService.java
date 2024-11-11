package com.jcmbeng.fundtransfer.services;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import com.jcmbeng.fundtransfer.entities.Client;

import java.util.List;

/**
 * Service interface for managing {@link Client} entities.
 *
 * <p>This interface extends the {@link GenericService} to provide CRUD operations
 * specifically for {@link ClientDTO} data transfer objects.</p>
 *
 * @see GenericService
 * @see Client
 * @see ClientDTO
 */
public interface ClientService extends GenericService<ClientDTO> {
    // Additional methods specific to ClientService can be defined here if needed
}
