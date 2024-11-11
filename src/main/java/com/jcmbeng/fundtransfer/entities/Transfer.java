package com.jcmbeng.fundtransfer.entities;

import com.jcmbeng.fundtransfer.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The {@code Transfer} entity represents a money transfer between two accounts.
 * <p>
 * This class is annotated with {@link Entity} to map it to a database table, and it contains fields
 * that hold information about the transfer, including source and target accounts, transfer amount,
 * converted amount, exchange rate, fee, and timestamp.
 * </p>
 */
@Entity
@Table(name = "transfers")
@Data
@AllArgsConstructor
@Builder
public class Transfer extends AbstractEntity {

    @Column(name = "transfer_reference", nullable = false)
    private String transferReference;

    @OneToOne
    @JoinColumn(name = "debit_reference", nullable = false)
    private Transaction debitReference;

    @OneToOne
    @JoinColumn(name = "credit_reference" , nullable = false)
    private Transaction creditReference;

    /**
     * The source account from which the transfer originates.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "from_account_id", nullable = false)
    private Account fromAccount;

    /**
     * The target account to which the transfer is directed.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "to_account_id", nullable = false)
    private Account toAccount;

    /**
     * The amount of money to be transferred.
     */
    @Column(name = "amount_sent", nullable = false)
    private BigDecimal amountSent;

    /**
     * The amount after currency conversion, if applicable.
     */
    @Column(name = "amount_received", nullable = false)
    private BigDecimal amountReceived;

    /**
     * The exchange rate used for currency conversion.
     */
    @Column(name = "exchange_rate", nullable = false)
    private BigDecimal exchangeRate;

    /**
     * The fee charged for the transfer, if applicable. Default is zero.
     */
    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    /**
     * Optimistic locking field to handle concurrent updates on the transfer record.
     */
    @Version
    private Integer version;

    /**
     * The transferStatus of the transfer, representing the current state of the transfer process.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "transfer_status", nullable = false)
    private TransferStatus transferStatus;

    /**
     * The timestamp marking when the transfer was initiated.
     */
    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timestamp;

    /**
     * Description or note about the transaction.
     */
    @Column(name = "description",  nullable = false)
    private String description;


    public Transfer() {
        this.fee = BigDecimal.ZERO;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransferReference () {
        return transferReference;
    }

    public void setTransferReference (String transferReference) {
        this.transferReference = transferReference;
    }

    public Transaction getDebitReference () {
        return debitReference;
    }

    public void setDebitReference (Transaction debitReference) {
        this.debitReference = debitReference;
    }

    public Transaction getCreditReference () {
        return creditReference;
    }

    public void setCreditReference (Transaction creditReference) {
        this.creditReference = creditReference;
    }

    public Account getFromAccount () {
        return fromAccount;
    }

    public void setFromAccount (Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount () {
        return toAccount;
    }

    public void setToAccount (Account toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmountSent () {
        return amountSent;
    }

    public void setAmountSent (BigDecimal amountSent) {
        this.amountSent = amountSent;
    }

    public BigDecimal getAmountReceived () {
        return amountReceived;
    }

    public void setAmountReceived (BigDecimal amountReceived) {
        this.amountReceived = amountReceived;
    }

    public BigDecimal getExchangeRate () {
        return exchangeRate;
    }

    public void setExchangeRate (BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getFee () {
        return fee != null ? fee : BigDecimal.ZERO;
    }

    public void setFee (BigDecimal fee) {
        this.fee = fee != null ? fee : BigDecimal.ZERO;
    }

    public Integer getVersion () {
        return version;
    }

    public void setVersion (Integer version) {
        this.version = version;
    }

    public TransferStatus getTransferStatus () {
        return transferStatus;
    }

    public void setTransferStatus (TransferStatus transferStatus) {
        this.transferStatus = transferStatus;
    }

    public LocalDateTime getTimestamp () {
        return timestamp;
    }

    public void setTimestamp (LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

}
