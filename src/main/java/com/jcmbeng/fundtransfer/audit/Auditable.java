package com.jcmbeng.fundtransfer.audit;

import java.time.LocalDateTime;

/**
 * Interface for auditable entities.
 * Provides methods to get and set creation and update timestamps.
 */
public interface Auditable {

    /**
     * Gets the creation timestamp of the entity.
     *
     * @return The creation timestamp.
     */
    LocalDateTime getCreatedAt();

    /**
     * Sets the creation timestamp of the entity.
     *
     * @param createdAt The creation timestamp to set.
     */
    void setCreatedAt(LocalDateTime createdAt);

    /**
     * Gets the update timestamp of the entity.
     *
     * @return The update timestamp.
     */
    LocalDateTime getUpdatedAt();

    /**
     * Sets the update timestamp of the entity.
     *
     * @param updatedAt The update timestamp to set.
     */
    void setUpdatedAt(LocalDateTime updatedAt);
}

