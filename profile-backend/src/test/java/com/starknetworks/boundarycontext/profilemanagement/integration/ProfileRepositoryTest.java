package com.starknetworks.boundarycontext.profilemanagement.integration;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.*;
import com.starknetworks.boundarycontext.profilemanagement.entity.ProfileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Test
    public void testRetrieveProfile() {
        assertThat(profileRepository.findAll()).isNotEmpty();
    }

    @Test
    public void testCreateProfileAndFindById() throws ParseException, IllegalAccessException, InstantiationException {
        // given new profile
        ProfileEntity newProfile = generateNewProfile();

        // when saving new profile
        ProfileEntity savedProfile = profileRepository.save(newProfile);

        // test that profile is saved and retrieved successfully
        assertThat(profileRepository.findById(savedProfile.getId()).get())
                .isEqualTo(savedProfile);
    }

    private ProfileEntity generateNewProfile() throws ParseException, InstantiationException, IllegalAccessException {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setDisplayName("JD");
        profileEntity.setRealName("John Doe");
        profileEntity.setAbout("Just a regular joe");
        profileEntity.setHeight(1.4);
        profileEntity.setOccupation("Software engineer");
        profileEntity.setAvatar("picture".getBytes());
        profileEntity.setCity(SingleValueEntity.of(CityEntity.class, -1L, ""));
        profileEntity.setGender(SingleValueEntity.of(GenderEntity.class, -1L, ""));
        profileEntity.setMaritalStatus(SingleValueEntity.of(MaritalStatusEntity.class, -1L, ""));
        profileEntity.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-14"));
        profileEntity.setFigure(SingleValueEntity.of(FigureEntity.class, -1L, ""));
        profileEntity.setReligion(SingleValueEntity.of(ReligionEntity.class, -1L, ""));
        profileEntity.setEthnicity(SingleValueEntity.of(EthnicityEntity.class, -1L, ""));
        return profileEntity;
    }
}
