package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import com.starknetworks.crosscutting.jpa.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class SingleValueEntity extends BaseEntity {
    @Column(name = "name", insertable = false, updatable = false)
    private String name;

    public static <T extends SingleValueEntity> T of(Class<T> clazz, Long id, String name)
            throws IllegalAccessException, InstantiationException {
        T entity = clazz.newInstance();
        entity.setId(id);
        entity.setName(name);
        return entity;
    }
}
