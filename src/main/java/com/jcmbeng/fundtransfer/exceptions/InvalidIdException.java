package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

import java.util.ArrayList;

public class InvalidIdException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public InvalidIdException(String message, CustomMessage customMessage) {
        super(message, customMessage);
    }

    public InvalidIdException(CustomMessage customMessage) {
        super(customMessage.getMessage(), customMessage, new ArrayList<>());
    }
}