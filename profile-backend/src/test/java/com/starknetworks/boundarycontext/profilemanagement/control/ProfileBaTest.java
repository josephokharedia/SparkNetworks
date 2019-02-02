package com.starknetworks.boundarycontext.profilemanagement.control;

import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.CityTo;
import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.EthnicityTo;
import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.FigureTo;
import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.ReligionTo;
import com.starknetworks.boundarycontext.masterdatamanagement.entity.*;
import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import com.starknetworks.boundarycontext.profilemanagement.control.util.ImageUtil;
import com.starknetworks.boundarycontext.profilemanagement.entity.ProfileEntity;
import com.starknetworks.boundarycontext.profilemanagement.integration.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ProfileBa.class)
@TestPropertySource(locations = "classpath:localConfig.properties")
public class ProfileBaTest {

    @Autowired
    ProfileBa profileBa;

    @MockBean
    ProfileRepository profileRepository;

    @MockBean
    Logger logger;

    @MockBean
    ImageUtil imageUtil;

    @MockBean
    MultipartFile file;
    @MockBean
    EntityManager em;

    private static byte[] avatar = "avatar".getBytes();

    @Before
    public void setup() throws IOException {
        when(file.getBytes()).thenReturn(avatar);
    }

    @Test
    public void testGetProfile() {
        // given id of profile
        Long id = -1L;
        when(profileRepository.findById(id)).thenReturn(Optional.of(new ProfileEntity()));

        // when looking up profile with id
        profileBa.getProfile(id);

        // verify the findById is invoked
        verify(profileRepository).findById(id);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetProfileNotFound() {
        // given id of profile
        Long id = -99L;
        when(profileRepository.findById(id)).thenReturn(Optional.empty());

        // when looking up profile with id
        profileBa.getProfile(id);
    }

    @Test
    public void testSaveProfile() throws ParseException, InstantiationException, IllegalAccessException {
        // given profile to create
        ProfileTo profileTo = generateProfileTo();


        //when creating profile
        when(em.find(CityEntity.class, -1L)).thenReturn(SingleValueEntity.of(CityEntity.class, -1L, "Test city"));
        when(em.find(FigureEntity.class, -1L)).thenReturn(SingleValueEntity.of(FigureEntity.class, -1L, "Test figure"));
        when(em.find(EthnicityEntity.class, -1L)).thenReturn(SingleValueEntity.of(EthnicityEntity.class, -1L, "Test ethnicity"));
        when(em.find(ReligionEntity.class, -1L)).thenReturn(SingleValueEntity.of(ReligionEntity.class, -1L, "Test religion"));
        when(profileRepository.save(any(ProfileEntity.class))).thenReturn(new ProfileEntity());
        profileBa.saveProfile(profileTo);

        ArgumentCaptor<ProfileEntity> captor = ArgumentCaptor.forClass(ProfileEntity.class);

        //verify that save was called with the correct values
        verify(profileRepository).save(captor.capture());
        ProfileEntity savedProfile = captor.getValue();
        assertThat(savedProfile.getDisplayName()).isEqualTo("John");
        assertThat(savedProfile.getRealName()).isEqualTo("John Doe");
        assertThat(new SimpleDateFormat("yyyy-MM-dd").format(savedProfile.getBirthDate()))
                .isEqualTo("1987-07-14");
        assertThat(savedProfile.getHeight()).isEqualTo(1.4);
        assertThat(savedProfile.getAbout()).isEqualTo("Just a regular joe");
        assertThat(savedProfile.getCity().getId()).isEqualTo(-1L);
        assertThat(savedProfile.getFigure().getId()).isEqualTo(-1L);
        assertThat(savedProfile.getEthnicity().getId()).isEqualTo(-1L);
        assertThat(savedProfile.getReligion().getId()).isEqualTo(-1L);
    }

    @Test
    public void testSaveAvatar() throws IOException {
        // given avatar and profile
        when(imageUtil.scaleImage(any(byte[].class), anyInt(), anyInt(), anyDouble(), anyDouble()))
                .thenReturn(avatar);
        when(profileRepository.findById(anyLong())).thenReturn(Optional.of(new ProfileEntity()));
        when(profileRepository.save(any(ProfileEntity.class))).thenReturn(new ProfileEntity());

        // when saving avatar
        profileBa.saveAvatar(-1L, file);

        ArgumentCaptor<ProfileEntity> captor = ArgumentCaptor.forClass(ProfileEntity.class);
        //verify that save was called with the correct values
        verify(profileRepository).save(captor.capture());
        ProfileEntity savedProfile = captor.getValue();
        assertThat(savedProfile.getAvatar()).isEqualTo(avatar);
    }


    private ProfileTo generateProfileTo() throws ParseException {
        return ProfileTo.builder()
                .displayName("John")
                .realName("John Doe")
                .birthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1987-07-14"))
                .height(1.4)
                .about("Just a regular joe")
                .city(new CityTo(-1L, "New york"))
                .ethnicity(new EthnicityTo(-1L, "Black"))
                .figure(new FigureTo(-1L, "Slim"))
                .religion(new ReligionTo(-1L, "Christianity"))
                .build();
    }

}
