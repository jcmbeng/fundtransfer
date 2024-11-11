package com.jcmbeng.fundtransfer.exceptions;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.RESOURCE_NOT_FOUND;

/**
 * Exception thrown when a requested resource is not found.
 * Extends the {@code CustomException} class to provide specific error conditions.
 */
public class ResourceNotFoundException extends CustomException {

    /**
     * {@inheritDoc}
     */
    public ResourceNotFoundException(String message) {
        super(message, RESOURCE_NOT_FOUND);
    }
}
