package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sn_marital_status")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class MaritalStatusEntity extends SingleValueEntity {
}
