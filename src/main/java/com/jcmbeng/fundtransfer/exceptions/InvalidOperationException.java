package com.jcmbeng.fundtransfer.exceptions;

import com.jcmbeng.fundtransfer.enums.CustomMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class InvalidOperationException extends RuntimeException {

    @Getter
    private CustomMessage customMessage;

    @Getter
    private List<String> errors;

    @Getter
    private Exception exception;

    /**
     * @param exception
     */
    public InvalidOperationException(Exception exception) {
        super(exception.getMessage());
        this.exception = exception;

        this.errors = new ArrayList<>();
        this.customMessage = CustomMessage.UNKNOWN_ERROR;
    }


}