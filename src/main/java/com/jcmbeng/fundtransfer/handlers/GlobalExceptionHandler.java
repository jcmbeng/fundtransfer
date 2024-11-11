package com.jcmbeng.fundtransfer.handlers;

import com.jcmbeng.fundtransfer.dtos.ErrorDto;
import com.jcmbeng.fundtransfer.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.UUID;

import static com.jcmbeng.fundtransfer.enums.CustomMessage.UNKNOWN_ERROR;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    final Logger logger = LoggerFactory.getLogger (GlobalExceptionHandler.class);


    @ExceptionHandler(value = {
            Exception.class,
            RuntimeException.class
    })
    public ResponseEntity<?> runtimeExceptions (Exception exception) {
        final String guid = UUID.randomUUID ().toString ().toUpperCase ();
        logger.error (
                String.format ("\nError GUID=%s; error message: %s", guid, exception.getMessage ()),
                exception
        );
        final ErrorDto errorDto = ErrorDto.builder ()
                .GUID (guid)
                .code (UNKNOWN_ERROR)
                .errorCode (UNKNOWN_ERROR.getMessageCode ())
                .httpCode (UNKNOWN_ERROR.getHttpStatus ().value ())
                .message (UNKNOWN_ERROR.getMessage ())
                .errors (new ArrayList<> ())
                .build ();
        return new ResponseEntity<> (errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(
            value = {
                    InvalidOperationException.class
            })
    public ResponseEntity<?> runtimeExceptionHandler (InvalidOperationException exception) {
        final String guid = UUID.randomUUID ().toString ().toUpperCase ();
        logger.error (
                String.format ("\nError GUID=%s; error message: %s", guid, exception.getException ()),
                exception
        );
        final ErrorDto errorDto = ErrorDto.builder ()
                .GUID (guid)
                .code (UNKNOWN_ERROR)
                .errorCode (UNKNOWN_ERROR.getMessageCode ())
                .httpCode (exception.getCustomMessage ().getHttpStatus ().value ())
                .message (UNKNOWN_ERROR.getMessage ())
                .errors (exception.getErrors ())
                .build ();

        return new ResponseEntity<> (errorDto, exception.getCustomMessage ().getHttpStatus ());
    }


    @ExceptionHandler(value = {
            ResourceNotFoundException.class,
            UniqueConstraintException.class,
            InvalidIdException.class,
            ConstraintException.class,
            AccountException.class,
            ExchangeRateException.class,
            InvalidEntityException.class
    })
    public ResponseEntity<?> handleException (final CustomException exception) {
        final String guid = UUID.randomUUID ().toString ().toUpperCase ();
        logger.error (
                String.format ("\nError GUID=%s; error message: %s", guid, exception.getMessage ()),
                exception
        );
        final ErrorDto errorDto = ErrorDto.builder ()
                .GUID (guid)
                .code (exception.getCustomMessage ())
                .httpCode (exception.getCustomMessage ().getHttpStatus ().value ())
                .message (exception.getMessage ())
                .errorCode (exception.getCustomMessage ().getMessageCode ())
                .errors (exception.getErrors ())
                .build ();

        return new ResponseEntity<> (errorDto, exception.getCustomMessage ().getHttpStatus ());
    }

}
