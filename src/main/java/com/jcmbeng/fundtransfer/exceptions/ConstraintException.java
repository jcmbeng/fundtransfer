package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

public class ConstraintException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public ConstraintException(String message, CustomMessage customMessage) {
        super(message, customMessage);
    }
}