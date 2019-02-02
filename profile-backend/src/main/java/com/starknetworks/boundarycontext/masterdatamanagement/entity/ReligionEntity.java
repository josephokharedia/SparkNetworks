package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sn_religion")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ReligionEntity extends SingleValueEntity {
}
