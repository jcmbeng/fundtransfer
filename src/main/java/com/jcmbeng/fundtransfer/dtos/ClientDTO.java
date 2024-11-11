package com.jcmbeng.fundtransfer.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jcmbeng.fundtransfer.consts.Constants;
import com.jcmbeng.fundtransfer.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.jcmbeng.fundtransfer.consts.Constants.*;

/**
 * Data Transfer Object (DTO) for the {@link Client} entity.
 *
 * <p>This DTO is used to transfer client data, including name, email, phone number,
 * and timestamps for creation and last update. It includes validation constraints
 * and serialization rules for fields.</p>
 *
 * @see Client
 * @see Serializable
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the client.
     * This field is read-only and populated by the database.
     */
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    /**
     * Client's name. This field is required and has a maximum length of
     * {@link Constants#HUNDRED}.
     */
    @NotBlank(message = "The Name is required.")
    @Size(max = HUNDRED, message = "The name must have at most " + HUNDRED + " chars.")
    private String name;

    /**
     * Client's email address. This field is required and has a maximum length of
     * {@link Constants#HUNDRED}.
     */
    @NotBlank(message = "The Email is required.")
    @Size(max = HUNDRED, message = "The Email must have at most " + HUNDRED + " chars.")
    private String email;

    /**
     * Client's phone number. This field is required and has a maximum length of
     * {@link Constants#TWENTY}.
     */
    @NotBlank(message = "The Phone number is required.")
    @Size(max = TWENTY, message = "The Phone number must have at most " + TWENTY + " chars.")
    private String phoneNumber;

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

}
