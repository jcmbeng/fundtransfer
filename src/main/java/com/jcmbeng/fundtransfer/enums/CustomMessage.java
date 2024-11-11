package com.jcmbeng.fundtransfer.enums;


import org.springframework.http.HttpStatus;


public enum CustomMessage {
    //ERROR USER ERROR CODE BASE =1000
    RESOURCE_NOT_FOUND ("ERR-0404", "Resource not found", HttpStatus.NOT_FOUND),
    UNKNOWN_ERROR ("ERR-1001", "An unknown error has occurred, please check the logs with the error GUID for details.", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST_DATA ("ERR-0400", "Submitted data are not valid", HttpStatus.BAD_REQUEST),
    SUCCESSFUL ("", "SUCCESSFUL", HttpStatus.OK),


    CLIENT_DATA_INVALID ("ERR-1000", "Submitted data for client are not valid", HttpStatus.BAD_REQUEST),
    VALUE_ALREADY_USED ("ERR-1001", "[ %s ] is already used", HttpStatus.BAD_REQUEST),
    ID_NOT_VALID ("ERR-1002", "The provided ID is not valid", HttpStatus.BAD_REQUEST),

    BALANCE_NOT_SUFFICIENT ("MSG-1003", "The balance of account is not sufficient", HttpStatus.BAD_REQUEST),
    ACCOUNT_NUMBER_NOT_VALID ("MSG-1004", "The account number provided is not valid", HttpStatus.BAD_REQUEST),

    TRANSACTION_STATUS_NOT_VALID ("MSG-1005", "The transaction status provided is not valid",HttpStatus.BAD_REQUEST),
    TRANSACTION_METHOD_NOT_VALID ("MSG-1006", "The transaction method provided is not valid",HttpStatus.BAD_REQUEST),
    TRANSACTION_REFERENCE_NOT_VALID ("MSG-1007", "The transaction reference provided is not valid",
            HttpStatus.BAD_REQUEST),
    CONTENT_DATA_INVALID("MSG-1008", "Invalid data processing", HttpStatus.INTERNAL_SERVER_ERROR),
    NON_ACTIVE_ACCOUNT("MSG-1009", "The account is not active, please contact an administrator",
            HttpStatus.INTERNAL_SERVER_ERROR),

    EXCHANGE_RATE_ERROR("MSG-1010", "An error occurred while retrieving exchange rate ",
            HttpStatus.INTERNAL_SERVER_ERROR),

    RESOURCE_WITH_ID_NOT_FOUND ("ERR-9404", "%s with id [ %s ] not found", HttpStatus.NOT_FOUND),
    RESOURCE_DELETED ("MSG-0200", "Resource deleted successfully", HttpStatus.OK),
    RESOURCE_CREATED ("MSG-0201", "Resource created successfully", HttpStatus.CREATED),
    RESOURCE_UPDATED ("MSG-0202", "Resource updated successfully", HttpStatus.OK),
    RESOURCE_FOUND_WITH_ID ("MSG-0203", "Resource found", HttpStatus.OK),

    ;


    private final String message;
    private HttpStatus httpStatus;
    private String messageCode;

    CustomMessage (String messageCode, String message, HttpStatus httpStatus) {
        this.messageCode = messageCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }


    public String getMessage () {
        return message;
    }

    public HttpStatus getHttpStatus () {
        return httpStatus;
    }

    public String getMessageCode () {
        return messageCode;
    }
}
