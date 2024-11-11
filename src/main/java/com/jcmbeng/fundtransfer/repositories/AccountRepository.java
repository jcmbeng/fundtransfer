package com.jcmbeng.fundtransfer.repositories;

import com.jcmbeng.fundtransfer.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository interface for {@link Account} entity operations.
 * <p>
 * This interface extends {@link JpaRepository} for standard CRUD and otehers databses operations and
 * {@link JpaSpecificationExecutor}
 * to support dynamic query generation based on JPA Criteria API.
 * </p>
 *
 * @see Account
 * @see JpaRepository
 * @see JpaSpecificationExecutor
 */
public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    boolean existsByAccountNumber (String accountNumber);
    Optional<Account> findByAccountNumber (String accountNumber);
}
