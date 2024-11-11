package com.jcmbeng.fundtransfer.repositories;

import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for {@link Transaction} entity operations.
 * <p>
 * This interface extends {@link JpaRepository} for standard CRUD and otehers databses operations and
 * {@link JpaSpecificationExecutor}
 * to support dynamic query generation based on JPA Criteria API.
 * </p>
 *
 * @see Transaction
 * @see JpaRepository
 * @see JpaSpecificationExecutor
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
    Optional<Transaction> findByTransactionReference (String transactionReference);
}
