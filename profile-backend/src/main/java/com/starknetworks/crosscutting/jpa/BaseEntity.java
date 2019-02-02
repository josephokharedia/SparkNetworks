package com.starknetworks.crosscutting.jpa;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Base class for all entity with versioning
 */
@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseEntity {

    @Id
    @SequenceGenerator(name = "sq_id_generator", sequenceName = "sq_id_generator", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_generator")
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull
    @Version
    private Integer version;

    @PrePersist
    protected void setCreationParameters() {
        version = 0;
    }
}
