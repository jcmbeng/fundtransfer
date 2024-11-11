package com.jcmbeng.fundtransfer.repositories;

import com.jcmbeng.fundtransfer.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Repository interface for {@link Client} entity operations.
 * <p>
 * This interface extends {@link JpaRepository} for standard CRUD and otehers databses operations and
 * {@link JpaSpecificationExecutor}
 * to support dynamic query generation based on JPA Criteria API.
 * </p>
 *
 * @see Client
 * @see JpaRepository
 * @see JpaSpecificationExecutor
 */
public interface ClientRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
    boolean existsByEmail (String email);

    Optional<Client> findByEmail (String mail);
}
