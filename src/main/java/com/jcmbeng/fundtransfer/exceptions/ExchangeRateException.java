package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

/**
 * Exception thrown when a requested resource is not found.
 * Extends the {@code CustomException} class to provide specific error conditions.
 */
public class ExchangeRateException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public ExchangeRateException (String message, CustomMessage customMessage) {
        super(message,customMessage );
    }
}
