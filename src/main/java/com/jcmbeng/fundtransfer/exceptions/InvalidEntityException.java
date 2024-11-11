package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

import java.util.List;

public class InvalidEntityException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public InvalidEntityException(CustomMessage customMessage) {
        super(customMessage);
    }

    public InvalidEntityException(CustomMessage customMessage, List<String> errors) {
        super(customMessage, errors);
    }
}