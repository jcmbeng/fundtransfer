package com.jcmbeng.fundtransfer.enums;

/**
 * Represents the various statuses that an account can have in the system.
 */
public enum AccountStatus {

    /**
     * Indicates that the account is active and fully operational.
     */
    ACTIVE,

    /**
     * Indicates that the account is inactive, meaning it may not be used until reactivated.
     */
    INACTIVE,

    /**
     * Indicates that the account is temporarily suspended, usually due to security or policy reasons.
     */
    SUSPENDED,

    /**
     * Indicates that the account is permanently closed and cannot be reactivated.
     */
    CLOSED
}

