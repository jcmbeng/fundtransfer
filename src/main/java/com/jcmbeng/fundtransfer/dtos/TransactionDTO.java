package com.jcmbeng.fundtransfer.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransactionType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class TransactionDTO {

    /**
     * Unique identifier for the client.
     * This field is read-only and populated by the database.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /**
     * Unique identifier for the client.
     * This field is read-only and populated by the database.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String transactionReference;

    @NotNull(message = "Account Number cannot be null.")
    private String accountNumber;

    @NotNull(message = "Transaction method cannot be null.")
    private TransactionMethod method;

    @NotNull(message = "Amount cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero.")
    private BigDecimal amount;


    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;

    @NotNull(message = "Transaction status cannot be null.")
    private TransactionStatus status;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TransactionType transactionType;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String sourceReference;

}
