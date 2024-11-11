package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.BALANCE_NOT_SUFFICIENT;

/**
 * Exception thrown when a requested resource is not found.
 * Extends the {@code CustomException} class to provide specific error conditions.
 */
public class AccountException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public AccountException (String message, CustomMessage customMessage) {
        super(message,customMessage );
    }
}
