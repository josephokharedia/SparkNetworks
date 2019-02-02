package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "sn_ethnicity")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class EthnicityEntity extends SingleValueEntity {
}
