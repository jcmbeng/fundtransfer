package com.jcmbeng.fundtransfer.audit;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class AuditListener {
    @PrePersist
    public void prePersist(Auditable entity) {
        entity.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(Auditable entity) {
        entity.setUpdatedAt(LocalDateTime.now());
    }
}
