package com.jcmbeng.fundtransfer.entities;


import com.jcmbeng.fundtransfer.audit.AuditListener;
import com.jcmbeng.fundtransfer.audit.Auditable;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditListener.class)
/**
 * Represents an abstract base class for entities, providing common auditing fields.
 */
public class AbstractEntity implements Auditable, Serializable {

    /**
     * The unique identifier for the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // DEV
    private Long id;

    /**
     * The timestamp when the entity was created.
     * This field is not updatable.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the entity was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Indicates whether the entity is deleted.
     */
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;



}
