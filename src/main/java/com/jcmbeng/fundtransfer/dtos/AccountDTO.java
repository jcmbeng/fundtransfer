package com.jcmbeng.fundtransfer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcmbeng.fundtransfer.entities.Account;
import com.jcmbeng.fundtransfer.enums.AccountStatus;
import com.jcmbeng.fundtransfer.validators.EnumNamePattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

import static com.jcmbeng.fundtransfer.consts.Constants.*;

/**
 * Data Transfer Object (DTO) for the {@link Account} entity.
 *
 * <p>This DTO is used to transfer client data, including name, email, phone number,
 * and timestamps for creation and last update. It includes validation constraints
 * and serialization rules for fields.</p>
 *
 * @see Account
 * @see Serializable
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 1L;


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
    private String accountNumber;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal totalBalance;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal usableBalance;

    /**
     * The creation date of the client record. This field is read-only.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String creationDate;

    /**
     * The last update date of the client record. This field is read-only.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String lastUpdateDate;

    @NotNull(message = "The Account Owner is required and must exist.")
    private Long accountOwner;

    @NotNull(message = "Currency cannot be null.")
    @NotBlank(message = "Currency cannot be empty.")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be a valid ISO 4217 code (3 uppercase letters).")
    private String currency;

    @EnumNamePattern(regexp = "ACTIVE|INACTIVE|SUSPENDED|CLOSED")
    private AccountStatus accountStatus;


}
