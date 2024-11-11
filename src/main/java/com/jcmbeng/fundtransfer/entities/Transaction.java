package com.jcmbeng.fundtransfer.entities;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The {@code Account} entity represents a financial account belonging to a {@link Client}.
 * <p>
 * This class is annotated with {@link Entity} to map it to a database table, with fields representing
 * the account’s unique ID, owner, currency, and balance details.
 * </p>
 */
@Entity
@Table(name = "transactions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "source_reference")
    private String sourceReference;

    @Column(name = "transaction_reference", nullable = false)
    private String transactionReference;

    /**
     * The account associated with this transaction.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    /**
     * The type of transaction (e.g., DEBIT or CREDIT).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType type;

    /**
     * The amount involved in this transaction.
     */
    @Column(name = "amount",  nullable = false)
    private BigDecimal amount;

    /**
     * The status of the transaction (e.g., SUCCESS, FAILED, PENDING).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status",  nullable = false)
    private TransactionStatus status;

    /**
     * A timestamp marking when the transaction occurred.
     */
    @Column(name = "transaction_time")
    private LocalDateTime timestamp;

    /**
     * Description or note about the transaction.
     */
    @Column(name = "description",  nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_method")
    private TransactionMethod method;


    /**
     * Optimistic locking field to handle concurrent updates on the transfer record.
     */
    @Version
    private Integer version;
}