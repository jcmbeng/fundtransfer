package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;

public class UniqueConstraintException  extends CustomException {
    public UniqueConstraintException(CustomMessage customMessage) {
        super(customMessage.getMessage(), customMessage);
    }
}