package com.jcmbeng.fundtransfer.ws;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.dtos.TransactionDTO;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(
        value = "/api/v1/transactions"
)
public interface TransactionApi {

    @PostMapping("/credit")
    ResponseEntity<?> credit(@RequestBody TransactionDTO transactionDto);

    @PostMapping("/debit")
    ResponseEntity<?> debit(@RequestBody TransactionDTO transactionDto);

    @GetMapping("/{id}")
    ResponseEntity<?> getById(@PathVariable(name = "id") Long id);

    @GetMapping("transaction-reference/{reference}")
    ResponseEntity<?> getByTransactionReference(@PathVariable(name = "reference") String reference);

    @GetMapping
    ResponseEntity<?> findAllTransactions(  @RequestParam(required = false, name ="transactionType") TransactionType transactionType,
                                            @RequestParam(required = false, name ="transactionStatus") TransactionStatus transactionStatus,
                                            @RequestParam(required = false, name ="transactionMethod") TransactionMethod transactionMethod,
                                            @RequestParam(required = false, name ="accountNumber") String accountNumber,
                                            @RequestParam(required = false, name ="transactionDate") String transactionDate,
                                            @RequestParam(required = false, name ="page", defaultValue = "0") int page,
                                            @RequestParam(required = false, name ="size", defaultValue = "10") int size,
                                            @RequestParam(required = false, name ="sortBy", defaultValue = "id") String sortBy,
                                            @RequestParam(required = false, name ="direction", defaultValue = "ASC") String direction);

}
