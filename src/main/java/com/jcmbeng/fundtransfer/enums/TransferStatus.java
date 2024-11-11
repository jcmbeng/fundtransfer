package com.jcmbeng.fundtransfer.enums;

/**
 * The {@code TransferStatus} enum represents the various stages of a transfer's lifecycle.
 * <p>
 * Each status describes a specific state in the transfer process, from initiation to completion or failure.
 * </p>
 */
public enum TransferStatus {

    /**
     * The transfer request has been created and is awaiting further processing.
     */
    INITIATED,

    /**
     * The transfer is undergoing funds verification and currency conversion.
     */
    PENDING,

    /**
     * The transfer is actively being processed between accounts.
     */
    PROCESSING,

    /**
     * The transfer has successfully completed without issues.
     */
    COMPLETED,

    /**
     * The transfer failed due to an error or system issue.
     */
    FAILED,

    /**
     * The transfer was cancelled by the user or system before it began processing.
     */
    CANCELLED,

}
