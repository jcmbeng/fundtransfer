package com.jcmbeng.fundtransfer.dtos;

import com.jcmbeng.fundtransfer.enums.CustomMessage;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object (DTO) for encapsulating error details in responses.
 *
 * <p>This DTO provides structured information about an error, including
 * a unique identifier, HTTP status code, custom message, error code, and
 * additional error details.</p>
 *
 * @see CustomMessage
 * @see Serializable
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto implements Serializable {

    private static final long serialVersionUID = 491940608093603925L;

    /**
     * Unique identifier for the error, typically used for tracing.
     */
    private String GUID;

    /**
     * HTTP status code associated with the error response.
     */
    private Integer httpCode;

    /**
     * Custom message code indicating the specific type of error.
     * Uses {@link CustomMessage} enum for predefined messages.
     */
    private CustomMessage code;

    /**
     * Descriptive message explaining the error.
     */
    private String message;

    /**
     * Error code string for categorizing the error type.
     */
    private String errorCode;

    /**
     * List of additional error messages providing further context.
     */
    private List<String> errors = new ArrayList<>();

}