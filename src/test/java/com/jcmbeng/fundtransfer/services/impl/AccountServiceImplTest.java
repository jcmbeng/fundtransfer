package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.exceptions.ResourceNotFoundException;
import com.jcmbeng.fundtransfer.mappers.impl.AccountMapper;
import com.jcmbeng.fundtransfer.repositories.AccountRepository;
import com.jcmbeng.fundtransfer.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    private Account account_test;
    private AccountDTO accountDTO;
    private AccountDTO accountDTO_test;
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        account = new Account();
        account.setId(1L);
        account.setAccountNumber("123456789");
        account.setCurrency("USD");
        account.setOwner(client);
        account.setAccountStatus(AccountStatus.ACTIVE);

        accountDTO = new AccountDTO();
        accountDTO.setAccountNumber("123456789");
        accountDTO.setCurrency("USD");
        accountDTO.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO.setAccountOwner(1L);

        account_test = new Account();
        account_test.setId(2L);
        account_test.setAccountNumber("987654321");
        account_test.setCurrency("EUR");
        account_test.setOwner(client);
        account_test.setAccountStatus(AccountStatus.ACTIVE);

        accountDTO_test = new AccountDTO();
        accountDTO_test.setAccountNumber("987654321");
        accountDTO_test.setCurrency("EUR");
        accountDTO_test.setAccountStatus(AccountStatus.ACTIVE);
        accountDTO_test.setAccountOwner(1L);


    }

    @Test
    void testCreateAccount_Successful() {
        //Given
        when(accountRepository.existsByAccountNumber(accountDTO.getAccountNumber())).thenReturn(false);
        when(clientRepository.findById(accountDTO.getAccountOwner())).thenReturn(Optional.of(client));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        // Action
        AccountDTO createdAccount = accountService.create(accountDTO);

        // Checking / Assertions
        assertNotNull(createdAccount);
        assertEquals("123456789", createdAccount.getAccountNumber());
        assertEquals("USD", createdAccount.getCurrency());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testCreateAccountTest_Successful() {
        //Given
        when(accountRepository.existsByAccountNumber(accountDTO_test.getAccountNumber())).thenReturn(false);
        when(clientRepository.findById(accountDTO_test.getAccountOwner())).thenReturn(Optional.of(client));
        when(accountRepository.save(any(Account.class))).thenReturn(account_test);

        // Action
        AccountDTO createdAccount = accountService.create(accountDTO_test);

        // Checking / Assertions
        assertNotNull(createdAccount);
        assertEquals("987654321", createdAccount.getAccountNumber());
        assertEquals("EUR", createdAccount.getCurrency());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testFindById_AccountExists() {
        //Given
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Action
        AccountDTO result = accountService.findById(1L);

        // Checking / Assertions
        assertNotNull(result);
        assertEquals(account.getAccountNumber(), result.getAccountNumber());
        assertEquals(account.getCurrency(), result.getCurrency());
    }

    @Test
    void testFindById_AccountDoesNotExist() {
        //Given
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        // Action & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountService.findById(1L);
        });
        assertEquals("Account with id [ 1 ] not found", exception.getMessage());
    }

    @Test
    void testUpdateAccount_Successful() {
        //Given
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountDTO.setCurrency("EUR");

        // Action
        AccountDTO updatedAccount = accountService.update(1L, accountDTO);

        // Checking / Assertions
        assertNotNull(updatedAccount);
        assertEquals("EUR", updatedAccount.getCurrency());
        verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteAccount_Successful() {
        //Given
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        // Action
        String response = accountService.delete(1L);

        // Checking / Assertions
        assertEquals("Resource deleted successfully", response);
        assertTrue(account.getIsDeleted());
        verify(accountRepository, times(1)).save(account);
    }


    @Test
    void testGetByAccountNumber_AccountExists() {
        //Given
        when(accountRepository.findByAccountNumber("123456789")).thenReturn(Optional.of(account));

        // Action
        AccountDTO result = accountService.getByAccountNumber("123456789");

        // Checking / Assertions
        assertNotNull(result);
        assertEquals("123456789", result.getAccountNumber());
    }

    @Test
    void testGetByAccountNumber_AccountDoesNotExist() {
        //Given
        when(accountRepository.findByAccountNumber("123456789")).thenReturn(Optional.empty());

        // Action & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            accountService.getByAccountNumber("123456789");
        });
        assertEquals("Account Number with id [ 123456789 ] not found", exception.getMessage());
    }
}
