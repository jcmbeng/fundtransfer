package com.jcmbeng.fundtransfer.services;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.dtos.TransactionDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import jakarta.transaction.Transactional;

/**
 * Service interface for managing {@link Account} entities.
 *
 * <p>This interface extends the {@link GenericService} to provide CRUD operations
 * specifically for {@link AccountDTO} data transfer objects.</p>
 *
 * @see GenericService
 * @see Account
 * @see AccountDTO
 */
public interface TransactionService {


    @Transactional
    Transaction saveCredit (TransactionDTO dto);

    TransactionDTO credit(TransactionDTO transactionDto);
    
    TransactionDTO debit(TransactionDTO transactionDto);

    @Transactional
    Transaction saveDebit (TransactionDTO dto);

    TransactionDTO findById (Long id);

    TransactionDTO findByReference (String reference);


    ResponsePaginatedAndSortedDTO findAll (TransactionType transactionType,
                                           TransactionStatus transactionStatus,
                                           TransactionMethod transactionMethod,
                                           String accountNumber,
                                           String transactionDate,
                                           PaginationAndSortDTO paginationAndSortDTO);
}
