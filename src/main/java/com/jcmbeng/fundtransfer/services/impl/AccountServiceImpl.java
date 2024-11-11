package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Client;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.enums.CriteriaFilter;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.exceptions.*;
import com.jcmbeng.fundtransfer.mappers.impl.AccountMapper;
import com.jcmbeng.fundtransfer.mappers.impl.TransactionMapper;
import com.jcmbeng.fundtransfer.repositories.AccountRepository;
import com.jcmbeng.fundtransfer.repositories.ClientRepository;
import com.jcmbeng.fundtransfer.repositories.specifications.AccountSpecification;
import com.jcmbeng.fundtransfer.repositories.specifications.TransactionSpecification;
import com.jcmbeng.fundtransfer.services.AccountService;
import com.jcmbeng.fundtransfer.validators.DtoValidator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.*;
import static com.jcmbeng.fundtransfer.utils.StringUtil.resourceNotFoundWithId;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public AccountServiceImpl (AccountRepository accountRepository,
                               ClientRepository clientRepository) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public AccountDTO create (final AccountDTO dto) {
        DtoValidator.validate (dto);


        try {
            if (accountRepository.existsByAccountNumber (dto.getAccountNumber ())) {
                throw new UniqueConstraintException (VALUE_ALREADY_USED);
            }
          Optional<Client> optionalClient =  clientRepository.findById (dto.getAccountOwner ());
            if(optionalClient.isEmpty ()){
                throw new UniqueConstraintException (VALUE_ALREADY_USED);
            }
            Account account = AccountMapper.builder ().build ().toEntity (dto);
            account.setOwner (optionalClient.get ());

            return AccountMapper.builder ()
                    .build ()
                    .toDto (
                            accountRepository.save (account)
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public ResponsePaginatedAndSortedDTO findAll (PaginationAndSortDTO paginationAndSortDTO) {
        try {
            Page<Account> accounts =  accountRepository.findAll (paginationAndSortDTO.toPageable ());
            return ResponsePaginatedAndSortedDTO.pagedDto (accounts, new AccountMapper ());
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public AccountDTO findById (final Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {


            return accountRepository
                    .findById (id)
                    .map (account -> {
                        return AccountMapper.builder ().build ().toDto (account);
                    })
                    .orElseThrow (
                            () -> new ResourceNotFoundException (resourceNotFoundWithId ("Account", id))
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


    @Override
    public AccountDTO update (final Long id, final AccountDTO dto) {
        DtoValidator.validate (dto);

        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {
            Optional<Account> optionalAccount = accountRepository.findById (id);
            if (optionalAccount.isEmpty ()) {
                throw new ResourceNotFoundException (resourceNotFoundWithId ("Account", id));
            }
            Account account = optionalAccount.get ();
            account.setCurrency (dto.getCurrency ());
            account.setAccountStatus (dto.getAccountStatus ());

            return AccountMapper.builder ().build ().toDto (
                    account
            );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }


    @Override
    public String delete (final Long id) {
        if (id <= 0 || id == null) {
            throw new InvalidIdException (ID_NOT_VALID);
        }
        try {
            Optional<Account> optionalAccount = accountRepository.findById (id);
            if (optionalAccount.isEmpty ()) {
                throw new ResourceNotFoundException ("Unable to find a Account with id " + id);
            }
            Account account = optionalAccount.get ();
            account.setIsDeleted (true);
            accountRepository.save (account);
            return RESOURCE_DELETED.getMessage ();
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Transactional
    @Override
    public ResponsePaginatedAndSortedDTO getByAccountStatus ( final  PaginationAndSortDTO paginationAndSortDTO ) {
        if (paginationAndSortDTO.getValueToSearch () == null || paginationAndSortDTO.getValueToSearch ().isBlank ()) {
            throw new InvalidIdException (TRANSACTION_METHOD_NOT_VALID);
        }
        try {

            Specification<Account> spec =
                    Specification.where(
                            AccountSpecification
                                    .hasStatus (AccountStatus.valueOf (paginationAndSortDTO.getValueToSearch ()))
                    );
            Page<Account> transactions = accountRepository.findAll (spec, paginationAndSortDTO.toPageable ());
            return ResponsePaginatedAndSortedDTO.pagedDto ( transactions, new AccountMapper () );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }

    @Override
    public AccountDTO getByAccountNumber (String accountNumber) {
        if (accountNumber == null || accountNumber.isBlank ()) {
            throw new InvalidIdException (ACCOUNT_NUMBER_NOT_VALID);
        }
        try {


            return accountRepository
                    .findByAccountNumber (accountNumber)
                    .map (account -> {
                        return AccountMapper.builder ().build ().toDto (account);
                    })
                    .orElseThrow (
                            () -> new ResourceNotFoundException (resourceNotFoundWithId ("Account Number",
                                    accountNumber))
                    );

        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }
}
