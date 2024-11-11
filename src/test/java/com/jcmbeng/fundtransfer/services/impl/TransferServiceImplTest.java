package com.jcmbeng.fundtransfer.services.impl;

import static com.jcmbeng.fundtransfer.enums.ExchangeRateProvider.TEST_CURRENCY_API;
import static com.jcmbeng.fundtransfer.enums.ExchangeRateProvider.TEST_CURRENCY_API;
import static org.junit.jupiter.api.Assertions.*;

import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.enums.ExchangeRateProvider;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import com.jcmbeng.fundtransfer.exceptions.AccountException;
import com.jcmbeng.fundtransfer.factories.ExchangeRateFactory;
import com.jcmbeng.fundtransfer.repositories.AccountRepository;
import com.jcmbeng.fundtransfer.repositories.ClientRepository;
import com.jcmbeng.fundtransfer.repositories.TransferRepository;
import com.jcmbeng.fundtransfer.services.TestCurrencyApi;
import com.jcmbeng.fundtransfer.services.TransactionService;
import com.jcmbeng.fundtransfer.utils.BigDecimalUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class TransferServiceImplTest {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TransactionService transactionService;

    @Mock
    private ExchangeRateFactory exchangeRateFactory;

    @Mock
    TestCurrencyApi testCurrencyApi;

    @Autowired
    private TransferServiceImpl transferService;

    private Account fromAccount;
    private Account toAccount;
    private TransferDTO transferDTO;

    private Client clientFrom;
    private Client clientTo;
    @BeforeEach
    void setUp() {

        clientFrom = new Client();
        clientFrom.setId(1L);
        clientFrom.setName("John");
        clientFrom.setEmail("john@example.com");
        clientFrom.setPhoneNumber("0123456789");

        clientTo = new Client();
        clientTo.setId(2L);
        clientTo.setName("Doe");
        clientTo.setEmail("doe@example.com");
        clientTo.setPhoneNumber("1234567890");

        // Initialize accounts
        fromAccount = new Account();
        fromAccount.setAccountNumber("123456789");
        fromAccount.setCurrency("USD");
        fromAccount.setTotalBalance (BigDecimal.valueOf(1000));
        fromAccount.setUsableBalance (BigDecimal.valueOf(1000));
        fromAccount.setAccountStatus (AccountStatus.ACTIVE);
        fromAccount.setOwner (clientRepository.save (clientFrom));
        accountRepository.save(fromAccount);

        toAccount = new Account();
        toAccount.setAccountNumber("987654321");
        toAccount.setCurrency("EUR");
        toAccount.setTotalBalance(BigDecimal.valueOf(500));
        toAccount.setUsableBalance (BigDecimal.valueOf(500));
        toAccount.setAccountStatus (AccountStatus.ACTIVE);
        toAccount.setOwner (clientRepository.save (clientTo));
        accountRepository.save(toAccount);

        // Prepare transfer DTO
        transferDTO = TransferDTO.builder ().build ();
        transferDTO.setFromAccount(fromAccount.getAccountNumber());
        transferDTO.setToAccount(toAccount.getAccountNumber());
        transferDTO.setAmountSent(BigDecimal.valueOf(100));
        transferDTO.setDescription("Test transfer");
    }


    @Test
    void testMakeTransfer_sourceAccountNotFound() {
        // Arrange
        transferDTO.setFromAccount("invalid");

        // Act and Assert
        AccountException exception = assertThrows(AccountException.class,
                () -> transferService.makeTransfer(transferDTO, TEST_CURRENCY_API));
        assertEquals("Source Account Not Found", exception.getMessage());
    }

    @Test
    void testMakeTransfer_destinationAccountNotFound() {
        // Arrange
        transferDTO.setToAccount("invalid");

        // Act and Assert
        AccountException exception = assertThrows(AccountException.class,
                () -> transferService.makeTransfer(transferDTO, TEST_CURRENCY_API));
        assertEquals("Destination Account Not Found", exception.getMessage());
    }
}
