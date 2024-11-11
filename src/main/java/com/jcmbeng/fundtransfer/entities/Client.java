package com.jcmbeng.fundtransfer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The {@code Client} entity represents a client in the system who owns one or more accounts.
 * <p>
 * This class includes fields for the client's name, unique ID, email, and phone number, as well as a list of associated accounts.
 * </p>
 */
@Entity
@Table(name = "clients")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends AbstractEntity {



     /**
     * The name of the client.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * The email address of the client, used for notifications and contact.
     */
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    /**
     * The phone number of the client, used for contact purposes.
     */
    @Column(name = "phone_number", length = 100, nullable = false, unique = true)
    private String phoneNumber;

    // We can add more propperties , getters and setters instead of lombok if needed

    /**
     * List of accounts owned by this client.
     * <p>
     * Uses {@link OneToMany} with a mappedBy attribute referencing the 'owner' field in the {@link Account} class.
     * </p>
     */
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Account> accounts = new HashSet<> ();

}
