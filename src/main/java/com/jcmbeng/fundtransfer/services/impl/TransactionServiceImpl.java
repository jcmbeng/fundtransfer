package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.dtos.TransactionDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import com.jcmbeng.fundtransfer.exceptions.InvalidIdException;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.exceptions.ResourceNotFoundException;
import com.jcmbeng.fundtransfer.exceptions.AccountException;
import com.jcmbeng.fundtransfer.mappers.impl.TransactionMapper;
import com.jcmbeng.fundtransfer.repositories.AccountRepository;
import com.jcmbeng.fundtransfer.repositories.TransactionRepository;
import com.jcmbeng.fundtransfer.repositories.specifications.TransactionSpecification;
import com.jcmbeng.fundtransfer.services.TransactionService;
import com.jcmbeng.fundtransfer.utils.AccountUtil;
import com.jcmbeng.fundtransfer.utils.StringUtil;
import com.jcmbeng.fundtransfer.validators.DtoValidator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.*;
import static com.jcmbeng.fundtransfer.utils.StringUtil.resourceNotFoundWithId;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionServiceImpl (TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public Transaction saveCredit (final TransactionDTO dto){
        DtoValidator.validate (dto);
        try{

            Optional<Account> optionalAccount = this.accountRepository.findByAccountNumber (dto.getAccountNumber ());
            if(optionalAccount.isEmpty ()){
                throw  new AccountException ("Destination Account Not Found", ACCOUNT_NUMBER_NOT_VALID);
            }
            Account account = optionalAccount.get ();

            AccountUtil.checkActivateStatus (account);

            Transaction transaction = TransactionMapper.builder ()
                    .build ()
                    .toEntity (dto);
            String transactionReference =  StringUtil.referenceGenerator ("CR");
            transaction.setTransactionReference (transactionReference);
            transaction.setAccount (account);
            transaction.setType (TransactionType.CREDIT);
            Transaction savedTransaction = transactionRepository.save (transaction);
            account.setTotalBalance (account.getTotalBalance ().add (savedTransaction.getAmount ()));
            accountRepository.save (account);
            return savedTransaction ;
        }  catch (
                InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


    @Override
    public TransactionDTO credit ( final TransactionDTO dto) {
        return  TransactionMapper.builder ()
                .build ()
                .toDto (this.saveCredit (dto) );
    }


    @Override
    public TransactionDTO debit ( final TransactionDTO dto) {
            return TransactionMapper.builder ()
                .build ()
                .toDto (this.saveDebit (dto) );
    }

    @Transactional
    @Override
    public Transaction saveDebit (final TransactionDTO dto){
        DtoValidator.validate (dto);
        try{

            Optional<Account> optionalAccount = this.accountRepository.findByAccountNumber (dto.getAccountNumber ());
            if(optionalAccount.isEmpty ()){
                throw  new AccountException ("Source Account Not Found", ACCOUNT_NUMBER_NOT_VALID);
            }
            Account account = optionalAccount.get ();

            AccountUtil.checkActivateStatus (account);

            if(!AccountUtil.canWithdraw (account, dto.getAmount ())){
                throw new AccountException ("Unable to perform the DEBIT/TRANSFER , check your balance",
                        BALANCE_NOT_SUFFICIENT);
            }

            Transaction transaction = TransactionMapper.builder ()
                    .build ()
                    .toEntity (dto);
            transaction.setAccount (account);
            transaction.setType (TransactionType.DEBIT);
            String transactionReference = StringUtil.referenceGenerator ("DE");
            transaction.setTransactionReference (transactionReference);
            Transaction savedTransaction = transactionRepository.save (transaction);
            account.setTotalBalance (account.getTotalBalance ().subtract (savedTransaction.getAmount ()));
           return savedTransaction;

        }  catch (
                InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Transactional
    @Override
    public TransactionDTO findById (final Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {


            return transactionRepository
                    .findById (id)
                    .map (transaction -> {
                        return TransactionMapper.builder ().build ().toDto (transaction);
                    })
                    .orElseThrow (
                            () -> new ResourceNotFoundException (resourceNotFoundWithId ("Transacton", id))
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public TransactionDTO findByReference (String transactionReference) {
        if (transactionReference ==null || transactionReference.isBlank ()) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {


            return transactionRepository
                    .findByTransactionReference (transactionReference)
                    .map (transaction -> {
                        return TransactionMapper.builder ().build ().toDto (transaction);
                    })
                    .orElseThrow (
                            () -> new ResourceNotFoundException (resourceNotFoundWithId ("Transacton", transactionReference))
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Transactional
    @Override
    public ResponsePaginatedAndSortedDTO findAll (
            final TransactionType transactionType,
            final TransactionStatus transactionStatus,
            final TransactionMethod transactionMethod,
            final String accountNumber,
            final String transactionDate,
            final  PaginationAndSortDTO paginationAndSortDTO ) {

        Specification<Transaction> specification = Specification.where(
                TransactionSpecification.hasTransactionType (transactionType)
                        .and (
                                TransactionSpecification.hasAccountAccountNumber (accountNumber )
                        )
                        .and (
                                TransactionSpecification.hasTransactionStatus (transactionStatus)
                        )
                        .and (
                                TransactionSpecification.hasTransactionMethod (transactionMethod)
                        )
        );

        try {
            Page<Transaction> transactions =  transactionRepository
                    .findAll (specification, paginationAndSortDTO.toPageable ());
            return ResponsePaginatedAndSortedDTO.pagedDto (transactions, new TransactionMapper ());
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


}
