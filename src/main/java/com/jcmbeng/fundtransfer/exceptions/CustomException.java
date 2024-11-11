package com.jcmbeng.fundtransfer.exceptions;


import com.jcmbeng.fundtransfer.enums.CustomMessage;

import java.util.ArrayList;
import java.util.List;


/**
 * Custom exception class that extends {@code RuntimeException}.
 * Used to indicate specific error conditions in the application.
 */
public class CustomException extends RuntimeException {

    private final String message;
    private final CustomMessage customMessage;
    private final List<String> errors;


    /**
     * Constructs a new custom exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param customMessage
     */
    public CustomException(CustomMessage customMessage) {
        super(customMessage.getMessage());
        this.message = customMessage.getMessage();
        this.customMessage = customMessage;
        this.errors = new ArrayList<>();
    }

    /**
     * * Constructs a new custom exception with the specified detail message.
     *   The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     * @param customMessage
     * @param errors
     */
    public CustomException(CustomMessage customMessage, List<String> errors) {
        super(customMessage.getMessage());
        this.message = customMessage.getMessage();
        this.customMessage = customMessage;
        this.errors = errors;
    }

    /**
     * Constructs a new custom exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message; saved for later retrieval by the {@link #getMessage()} method
     */
    public CustomException(String message) {
        super(message);
        this.message = message;
        this.customMessage = null;
        this.errors = new ArrayList<>();
    }

    /**
     * Constructs a new custom exception with the specified detail message and cause.
     *
     * @param message the detail message; saved for later retrieval by the {@link #getMessage()} method
     * @param cause   the cause; saved for later retrieval by the {@link #getCause()} method
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.customMessage = null;
        this.errors = new ArrayList<>();
    }

    /**
     * Constructs a new custom exception with the specified detail message and custom message.
     *
     * @param message       the detail message; saved for later retrieval by the {@link #getMessage()} method
     * @param customMessage the custom message object providing additional context; saved for later retrieval by the {@link #getCustomMessage()} method
     */
    public CustomException(String message, CustomMessage customMessage) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
        this.errors = new ArrayList<>();
    }

    /**
     * Constructs a new custom exception with the specified detail message, custom message, and list of errors.
     *
     * @param message       the detail message; saved for later retrieval by the {@link #getMessage()} method
     * @param customMessage the custom message object providing additional context; saved for later retrieval by the {@link #getCustomMessage()} method
     * @param errors        a list of error messages providing additional details; saved for later retrieval by the {@link #getErrors()} method
     */
    public CustomException(String message, CustomMessage customMessage, List<String> errors) {
        super(message);
        this.message = message;
        this.customMessage = customMessage;
        this.errors = errors;
    }

    /**
     * Retrieves the detail message of this exception.
     *
     * @return the detail message of this exception
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Retrieves the custom message of this exception.
     *
     * @return the custom message of this exception, or {@code null} if none was provided
     */
    public CustomMessage getCustomMessage() {
        return customMessage;
    }

    /**
     * Retrieves the list of errors of this exception.
     *
     * @return the list of errors of this exception, or {@code null} if none were provided
     */
    public List<String> getErrors() {
        return errors;
    }
}