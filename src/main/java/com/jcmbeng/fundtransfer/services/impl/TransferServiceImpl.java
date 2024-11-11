package com.jcmbeng.fundtransfer.services.impl;

import com.jcmbeng.fundtransfer.dtos.*;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transaction;
import com.jcmbeng.fundtransfer.entities.Transfer;
import com.jcmbeng.fundtransfer.enums.*;
import com.jcmbeng.fundtransfer.exceptions.AccountException;
import com.jcmbeng.fundtransfer.exceptions.ExchangeRateException;
import com.jcmbeng.fundtransfer.exceptions.InvalidOperationException;
import com.jcmbeng.fundtransfer.factories.ExchangeRateFactory;
import com.jcmbeng.fundtransfer.mappers.impl.TransferMapper;
import com.jcmbeng.fundtransfer.repositories.AccountRepository;
import com.jcmbeng.fundtransfer.repositories.TransferRepository;
import com.jcmbeng.fundtransfer.repositories.specifications.TransferSpecification;
import com.jcmbeng.fundtransfer.services.TransactionService;
import com.jcmbeng.fundtransfer.services.TransferService;
import com.jcmbeng.fundtransfer.utils.BigDecimalUtil;
import com.jcmbeng.fundtransfer.validators.DtoValidator;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.ACCOUNT_NUMBER_NOT_VALID;
import static com.jcmbeng.fundtransfer.utils.StringUtil.resourceNotFoundWithId;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final AccountRepository accountRepository;

    private final TransactionService transactionService;

    private final ExchangeRateFactory exchangeRateFactory;


    public TransferServiceImpl (TransferRepository transferRepository,
                                AccountRepository accountRepository,
                                TransactionService transactionService,
                                ExchangeRateFactory exchangeRateFactory) {
        this.transferRepository = transferRepository;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
        this.exchangeRateFactory = exchangeRateFactory;
    }



    @Override
    @Transactional
    public TransferDTO makeTransfer (TransferDTO dto, String rateExchangeProvider) {
        DtoValidator.validate (dto);
        try{
        Optional<Account> optionalFromAccount = this.accountRepository.findByAccountNumber (dto.getFromAccount ());
        if(optionalFromAccount.isEmpty ()){
            throw  new AccountException ("Source Account Not Found", ACCOUNT_NUMBER_NOT_VALID);
        }
        Account fromAccount = optionalFromAccount.get ();


        Optional<Account> optionalToAccount = this.accountRepository.findByAccountNumber (dto.getToAccount ());
        if(optionalToAccount.isEmpty ()){
            throw  new AccountException ("Destination Account Not Found", ACCOUNT_NUMBER_NOT_VALID);
        }
        Account toAccount = optionalToAccount.get ();

       // BigDecimal exchangeRate = StringUtil.getExchangeRate (fromAccount.getCurrency (), toAccount.getCurrency ());

            BigDecimal exchangeRate = exchangeRateFactory.getExchangeRate (
                    rateExchangeProvider,
                    fromAccount.getCurrency (),
                    toAccount.getCurrency ());


       TransactionDTO fromTransactionDTO = TransactionDTO.builder ()
               .accountNumber (dto.getFromAccount ())
               .amount (dto.getAmountSent ())
               .method (TransactionMethod.TRANSFER)
               .description (dto.getDescription ())
               .status (TransactionStatus.SUCCESS)
               .build ();
            Transaction debitReference = this.transactionService.saveDebit (fromTransactionDTO);


       BigDecimal amountToReceive = BigDecimalUtil.multiply (dto.getAmountSent (), exchangeRate);
       TransactionDTO toTransactionDTO = TransactionDTO.builder ()
                .accountNumber (dto.getToAccount ())
                .amount (amountToReceive)
                .method (TransactionMethod.TRANSFER)
                .description (dto.getDescription ())
                .status (TransactionStatus.SUCCESS)
                .build ();

            Transaction creditReference = this.transactionService.saveCredit (toTransactionDTO);


        Transfer transfer = TransferMapper.builder ().build ().toEntity (dto);

            transfer.setExchangeRate (exchangeRate);
            transfer.setFromAccount (fromAccount);
            transfer .setToAccount (toAccount);
            transfer .setAmountReceived (amountToReceive);
            transfer .setDescription (dto.getDescription ());
            transfer .setTransferStatus (TransferStatus.COMPLETED);
            transfer .setDebitReference (debitReference);
            transfer .setCreditReference (creditReference);


        return TransferMapper.builder ()
                    .build ()
                    .toDto ( transferRepository.save (transfer) );

    }  catch (
    InvalidOperationException exception) {
        throw new InvalidOperationException (exception);
    } catch (ExchangeRateException e) {
            throw new RuntimeException (e);
        }

    }



    @Override
    public TransferDTO findByReference (String reference) {
        return null;
    }

    @Override
    public ResponsePaginatedAndSortedDTO findAll (String fromAccount,
                                                  String toAccount,
                                                  String fromCurrency,
                                                  String toCurrency,
                                                  LocalDateTime startDate,
                                                  LocalDateTime endDate,
                                                  BigDecimal minAmount,
                                                  BigDecimal maxAmount,
                                                  TransferStatus transferStatus,
                                                  PaginationAndSortDTO paginationAndSortDTO) {

        Specification<Transfer> specification = Specification.where(
                TransferSpecification.hasFromAccount (fromAccount)
                        .and (
                                TransferSpecification.hasToAccount (toAccount )
                        )
                        .and (
                                TransferSpecification.hasFromCurrency (fromCurrency)
                        )
                        .and (
                                TransferSpecification.hasToCurrency (toCurrency)
                        )
                        .and (
                                TransferSpecification.hasTransferStatus (transferStatus)
                        )
        );
        try {
            Page<Transfer> transfers =  transferRepository
                    .findAll (specification, paginationAndSortDTO.toPageable ());
            return ResponsePaginatedAndSortedDTO.pagedDto (transfers, new TransferMapper ());
        } catch (InvalidOperationException exception) {
            throw new InvalidOperationException (exception);
        }
    }
}
