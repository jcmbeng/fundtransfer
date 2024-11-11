package com.jcmbeng.fundtransfer.services;

import com.jcmbeng.fundtransfer.dtos.AccountDTO;
import com.jcmbeng.fundtransfer.dtos.PaginationAndSortDTO;
import com.jcmbeng.fundtransfer.dtos.ResponsePaginatedAndSortedDTO;
import com.jcmbeng.fundtransfer.entities.Account;


public interface AccountService extends GenericService<AccountDTO> {
    ResponsePaginatedAndSortedDTO getByAccountStatus(PaginationAndSortDTO paginationAndSortDTO);

    AccountDTO getByAccountNumber (String accountNumber);
}
