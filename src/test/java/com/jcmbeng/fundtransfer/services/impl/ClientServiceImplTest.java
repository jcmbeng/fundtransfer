package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.ClientDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.mappers.impl.ClientMapper;
import com.jcmbeng.fundtransfer.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")  // Activates test profile for H2 configuration
@Transactional
class ClientServiceImplIntegrationTest {

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ClientRepository clientRepository;

    private ClientDTO clientDTO;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientDTO.setName("John Doe");
        clientDTO.setEmail("john.doe@example.com");
        clientDTO.setPhoneNumber("123456789");
    }

    @Test
    void testCreateClient_Successful() {
        // Act
        ClientDTO createdClient = clientService.create(clientDTO);

        // Assert
        assertNotNull(createdClient);
        assertEquals("John Doe", createdClient.getName());
        assertEquals("john.doe@example.com", createdClient.getEmail());

        Optional<Client> savedClient = clientRepository.findByEmail("john.doe@example.com");
        assertTrue(savedClient.isPresent());
        assertEquals("John Doe", savedClient.get().getName());
    }

    @Test
    void testFindById_ClientExists() {
        // Arrange
        Client client = ClientMapper.builder().build().toEntity(clientDTO);
        client = clientRepository.save(client);

        // Act
        ClientDTO foundClient = clientService.findById(client.getId());

        // Assert
        assertNotNull(foundClient);
        assertEquals("John Doe", foundClient.getName());
        assertEquals("john.doe@example.com", foundClient.getEmail());
    }

    @Test
    void testUpdateClient_Successful() {
        // Arrange
        Client client = ClientMapper.builder().build().toEntity(clientDTO);
        client = clientRepository.save(client);

        ClientDTO updatedDTO = new ClientDTO();
        updatedDTO.setName("John Smith");
        updatedDTO.setEmail("john.smith@example.com");
        updatedDTO.setPhoneNumber("987654321");

        // Act
        ClientDTO updatedClient = clientService.update(client.getId(), updatedDTO);

        // Assert
        assertNotNull(updatedClient);
        assertEquals("John Smith", updatedClient.getName());
        assertEquals("john.smith@example.com", updatedClient.getEmail());
    }

    @Test
    void testDeleteClient_Successful() {
        // Arrange
        Client client = ClientMapper.builder().build().toEntity(clientDTO);
        client = clientRepository.save(client);

        // Act
        String response = clientService.delete(client.getId());

        // Assert
        assertEquals("Resource deleted successfully", response);
        Optional<Client> deletedClient = clientRepository.findById(client.getId());
        assertTrue(deletedClient.isPresent());
        assertTrue(deletedClient.get().getIsDeleted());
    }

    @Test
    void testFindAllClients_PaginationAndSorting() {
        // Arrange
        Client client1 = new Client("Alice", "alice@example.com", "84848484", new HashSet<> ());
        Client client2 = new Client("Bob", "bob@example.com", "20202020", new HashSet<> ());
        clientRepository.save(client1);
        clientRepository.save(client2);

        PaginationAndSortDTO pagination = new PaginationAndSortDTO();
        pagination.setPage(0);
        pagination.setSize(2);

        // Act
        ResponsePaginatedAndSortedDTO response = clientService.findAll(pagination);

        // Assert
        assertNotNull(response);
        assertEquals(2, response.getTotalItems ());
    }
}
