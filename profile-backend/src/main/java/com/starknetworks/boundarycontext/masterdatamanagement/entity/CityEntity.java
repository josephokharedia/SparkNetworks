package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter(AccessLevel.PACKAGE)
@Entity
@Table(name = "sn_cities")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class CityEntity extends SingleValueEntity {

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;
}
