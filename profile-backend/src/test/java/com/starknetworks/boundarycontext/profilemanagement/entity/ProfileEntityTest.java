package com.starknetworks.boundarycontext.profilemanagement.entity;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProfileEntityTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void testThatProfileIsRetrievedSuccessfully() {
        ProfileEntity profile = em.find(ProfileEntity.class, -1L);
        assertThat(profile.getId()).isEqualTo(-1L);
    }

    @Test
    public void testThatProfileIsCreatedSuccessfully() throws ParseException {
        // given new profile
        ProfileEntity newProfile = generateNewProfile();

        // when creating profile
        ProfileEntity savedProfile = em.persistAndFlush(newProfile);

        // test that profile was created successfully
        assertThat(savedProfile.getId()).isNotNull();
        assertThat(savedProfile.getDisplayName()).isEqualTo("JD");
        assertThat(savedProfile.getRealName()).isEqualTo("John Doe");
        assertThat(savedProfile.getAbout()).isEqualTo("Just a regular joe");
        assertThat(savedProfile.getHeight()).isEqualTo(1.4);
        assertThat(savedProfile.getAvatar()).isEqualTo("picture".getBytes());
        assertThat(savedProfile.getOccupation()).isEqualTo("Software engineer");
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(savedProfile.getBirthDate()))
                .isEqualTo("1987-07-14");
        assertThat(savedProfile.getGender().getName()).isEqualTo("Test gender");
        assertThat(savedProfile.getCity().getName()).isEqualTo("Test city");
        assertThat(savedProfile.getFigure().getName()).isEqualTo("Test figure");
        assertThat(savedProfile.getEthnicity().getName()).isEqualTo("Test ethnicity");
        assertThat(savedProfile.getReligion().getName()).isEqualTo("Test religion");
    }

    @Test
    public void testThatProfileIsUpdatedSuccessfully() {
        // given profile
        ProfileEntity profile = em.find(ProfileEntity.class, -1L);
        assertThat(profile.getId()).isEqualTo(-1L);
        assertThat(profile.getDisplayName()).isEqualTo("Joe");

        // when updating profile
        profile.setDisplayName("Joey");
        ProfileEntity updatedProfile = em.merge(profile);

        // test that profile was updated successfully
        assertThat(updatedProfile.getDisplayName()).isEqualTo("Joey");
    }

    @Test
    public void testThatProfileIsRemovedSuccessfully() {
        // given profile
        ProfileEntity profile = em.find(ProfileEntity.class, -1L);
        assertThat(profile.getId()).isEqualTo(-1L);

        // when removing profile
        em.remove(profile);

        // test that profile was removed successfully
        assertThat(em.find(ProfileEntity.class, -1L)).isNull();
    }

    @Test
    public void testThatAvatarIsUploadedSuccessfully() {
        // given avatar and profile
        byte[] avatar = "picture".getBytes();
        ProfileEntity profile = em.find(ProfileEntity.class, -1L);

        // when saving avatar on profile
        profile.setAvatar(avatar);
        em.persistAndFlush(profile);

        // test that avatar on profile was saved successfully
        assertThat(em.find(ProfileEntity.class, -1L).getAvatar()).isEqualTo(avatar);

    }


    private ProfileEntity generateNewProfile() throws ParseException {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setDisplayName("JD");
        profileEntity.setRealName("John Doe");
        profileEntity.setAbout("Just a regular joe");
        profileEntity.setHeight(1.4);
        profileEntity.setOccupation("Software engineer");
        profileEntity.setAvatar("picture".getBytes());
        profileEntity.setCity(em.find(CityEntity.class, -1L));
        profileEntity.setGender(em.find(GenderEntity.class, -1L));
        profileEntity.setMaritalStatus(em.find(MaritalStatusEntity.class, -1L));
        profileEntity.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-14"));
        profileEntity.setFigure(em.find(FigureEntity.class, -1L));
        profileEntity.setReligion(em.find(ReligionEntity.class, -1L));
        profileEntity.setEthnicity(em.find(EthnicityEntity.class, -1L));
        return profileEntity;
    }

    @Test
    public void shouldEqual() {
        ProfileEntity profile1 = new ProfileEntity();
        profile1.setId(1L);
        profile1.setRealName("Joe");
        ProfileEntity profile2 = new ProfileEntity();
        profile2.setId(1L);
        profile2.setRealName("John");
        assertThat(profile1.equals(profile2)).isTrue();
    }
}
