package com.jcmbeng.fundtransfer.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcmbeng.fundtransfer.enums.TransactionMethod;
import com.jcmbeng.fundtransfer.enums.TransactionStatus;
import com.jcmbeng.fundtransfer.enums.TransferStatus;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import static com.jcmbeng.fundtransfer.consts.Constants.OPERATION_SCALE;
import static com.jcmbeng.fundtransfer.consts.Constants.ROUNDING_MODE;

@Data
@AllArgsConstructor
@Builder
public class TransferDTO {

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
    private String transferReference;

    @NotNull(message = "Source Account Number cannot be null.")
    private String fromAccount;

    @NotNull(message = "Destination Account Number cannot be null.")
    private String toAccount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fromCurrency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String toCurrency;

    @NotNull(message = "Amount sent cannot be null.")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero.")
    private BigDecimal amountSent;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal amountReceived;


    @NotBlank(message = "Description cannot be blank.")
    @Size(max = 255, message = "Description cannot exceed 255 characters.")
    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal exchangeRate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String debitReference;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creditReference;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String transactionDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private TransferStatus transferStatus;

    public BigDecimal getAmountSent (){
       return amountSent.setScale (OPERATION_SCALE, ROUNDING_MODE);
    }

}
