package com.starknetworks.boundarycontext.profilemanagement.control.transportobject;

import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileTo {
    private Long id;
    private Integer version;
    @NotBlank
    private String displayName;
    @NotBlank
    private String realName;
    @NotNull
    private Date birthDate;
    private Double height;
    private String occupation;
    private String about;
    private FigureTo figure;
    @NotNull
    private GenderTo gender;
    private ReligionTo religion;
    @NotNull
    private MaritalStatusTo maritalStatus;
    private EthnicityTo ethnicity;
    @NotNull
    private CityTo city;

}
