package com.starknetworks.crosscutting.jpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Base class for all entity with audits entries
 */
@Getter
@Setter
@MappedSuperclass
public class BaseAuditEntity extends BaseEntity {

    @NotNull
    @Column(name = "creation_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTimestamp;

    @NotNull
    @Column(name = "modification_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTimestamp;

    @PrePersist
    @Override
    protected void setCreationParameters() {
        Date date = new Date();
        creationTimestamp = date;
        modificationTimestamp = date;
    }

    @PreUpdate
    private void modificationAt() {
        modificationTimestamp = new Date();
    }
}
