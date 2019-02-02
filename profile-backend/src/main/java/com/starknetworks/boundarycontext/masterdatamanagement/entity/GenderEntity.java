package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sn_gender")
public class GenderEntity extends SingleValueEntity {
}
