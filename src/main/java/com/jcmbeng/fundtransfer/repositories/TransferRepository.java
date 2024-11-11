package com.jcmbeng.fundtransfer.repositories;

import com.jcmbeng.fundtransfer.dtos.TransferDTO;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Repository interface for {@link Transfer} entity operations.
 * <p>
 * This interface extends {@link JpaRepository} for standard CRUD and otehers databses operations and
 * {@link JpaSpecificationExecutor}
 * to support dynamic query generation based on JPA Criteria API.
 * </p>
 *
 * @see Transfer
 * @see JpaRepository
 * @see JpaSpecificationExecutor
 */
public interface TransferRepository extends JpaRepository<Transfer, Long>, JpaSpecificationExecutor<Transfer> {
    List<Transfer> findByFromAccount(Account account);
    List<Transfer> findByToAccount(Account account);


}
