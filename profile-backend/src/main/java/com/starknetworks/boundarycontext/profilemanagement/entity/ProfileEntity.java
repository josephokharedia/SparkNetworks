package com.starknetworks.boundarycontext.profilemanagement.entity;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.*;
import com.starknetworks.crosscutting.jpa.BaseAuditEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Entity representing a profile
 */
@Getter
@Setter
@Entity
@Table(name = "sn_profile")
public class ProfileEntity extends BaseAuditEntity {

    @NotNull
    @Size(max = 256)
    @Column(name = "display_name")
    private String displayName;

    @NotNull
    @Size(max = 256)
    @Column(name = "real_name")
    private String realName;

    @Column(name = "avatar")
    private byte[] avatar;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_gender", referencedColumnName = "id")
    private GenderEntity gender;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_ethnicity", referencedColumnName = "id")
    private EthnicityEntity ethnicity;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_religion", referencedColumnName = "id")
    private ReligionEntity religion;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_figure", referencedColumnName = "id")
    private FigureEntity figure;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_marital_status", referencedColumnName = "id")
    private MaritalStatusEntity maritalStatus;

    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "fk_city", referencedColumnName = "id")
    private CityEntity city;

    @Size(max = 5000)
    @Column(name = "about_me")
    private String about;

    @Column(updatable = false)
    private Double height;

    @Size(max = 256)
    @Column(name = "occupation")
    private String occupation;
}
