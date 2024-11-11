package com.jcmbeng.fundtransfer.entities;

import com.jcmbeng.fundtransfer.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The {@code Account} entity represents a financial account belonging to a {@link Client}.
 * <p>
 * This class is annotated with {@link Entity} to map it to a database table, with fields representing
 * the account’s unique ID, owner, currency, and balance details.
 * </p>
 */
@Entity
@Table(name = "accounts")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractEntity {




    /**
     * A unique account number generated for each account.
     */
    @Column(nullable = false, unique = true, updatable = false)
    private String accountNumber;

    /**
     * The status of the account, indicating whether it is active, inactive, suspended, or closed.
     * <p>
     * Uses {@link Enumerated} with {@link EnumType#STRING} to store the enum constant name as a string in the database,
     * ensuring easy readability and avoiding issues if the enum order changes in the future.
     * </p>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "account_status", nullable = false)
    private AccountStatus accountStatus;

    /**
     * The {@link Client} who owns this account.
     * <p>
     * Mapped with {@link ManyToOne} to establish a many-to-one relationship between accounts and clients.
     * {@code FetchType.LAZY} is used to fetch the client details on demand, and {@link JoinColumn} specifies
     * the foreign key column as {@code client_id}.
     * </p>
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", nullable = false, updatable = false)
    private Client owner;

    /**
     * The currency of the account (e.g., "USD", "EUR", "XAF").
     */
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    /**
     * The total balance of the account.
     */
    @Column(name = "total_balance", precision = 19, scale = 4, nullable = false)
    private BigDecimal totalBalance;

    /**
     * The usable balance of the account, representing the amount available for transactions.
     */
    @Column(name = "usable_balance", precision = 19, scale = 4, nullable = false)
    private BigDecimal usableBalance;


}
