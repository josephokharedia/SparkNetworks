package com.starknetworks.boundarycontext.profilemanagement.control;

import com.starknetworks.boundarycontext.masterdatamanagement.entity.*;
import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import com.starknetworks.boundarycontext.profilemanagement.control.util.ImageUtil;
import com.starknetworks.boundarycontext.profilemanagement.entity.ProfileEntity;
import com.starknetworks.boundarycontext.profilemanagement.integration.ProfileRepository;
import com.starknetworks.crosscutting.exceptions.BusinessException;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

/**
 * A business adapter that is responsible for managing profiles
 */
@Component
public class ProfileBa {

    private static Logger LOG;
    private ImageUtil imageUtil;
    private DozerBeanMapper mapper = new DozerBeanMapper();
    private final ProfileRepository profileRepository;
    @Value("${app.profile.avatar.width:100}")
    private int avatarWidth;
    @Value("${app.profile.avatar.height:100}")
    private int avatarHeight;
    @Value("${app.profile.avatar.x-factor-width:0.5}")
    private double avatarXFactorWidth;
    @Value("${app.profile.avatar.x-factor-height:0.5}")
    private double avatarXFactorHeight;
    private final EntityManager em;


    @Autowired
    public ProfileBa(Logger logger, ProfileRepository profileRepository,
                     ImageUtil imageUtil, EntityManager em) {
        this.profileRepository = profileRepository;
        LOG = logger;
        this.imageUtil = imageUtil;
        this.em = em;
    }

    /**
     * Save profile
     *
     * @param profileTo - profile to be saved
     * @return - profile version that has been saved
     */
    public ProfileTo saveProfile(ProfileTo profileTo) {
        ProfileEntity profileEntity;
        if (Objects.nonNull(profileTo.getId())) {
            profileEntity = profileRepository.findById(profileTo.getId())
                    .orElseThrow(EntityNotFoundException::new);
        } else {
            profileEntity = mapper.map(profileTo, ProfileEntity.class);
        }
        updateProperties(profileEntity, profileTo);
        profileRepository.save(profileEntity);
        return mapper.map(profileEntity, ProfileTo.class);
    }

    private void updateProperties(ProfileEntity profileEntity, ProfileTo profileTo) {
        profileEntity.setDisplayName(profileTo.getDisplayName());
        profileEntity.setRealName(profileTo.getRealName());
        profileEntity.setBirthDate(profileTo.getBirthDate());
        profileEntity.setHeight(profileTo.getHeight());
        profileEntity.setOccupation(profileTo.getOccupation());
        profileEntity.setAbout(profileTo.getAbout());
        profileEntity.setEthnicity(Optional.ofNullable(profileTo.getEthnicity())
                .map(x -> em.find(EthnicityEntity.class, x.getId()))
                .orElse(null));
        profileEntity.setFigure(Optional.ofNullable(profileTo.getFigure())
                .map(x -> em.find(FigureEntity.class, x.getId()))
                .orElse(null));
        profileEntity.setCity(Optional.ofNullable(profileTo.getCity())
                .map(x -> em.find(CityEntity.class, x.getId()))
                .orElse(null));
        profileEntity.setGender(Optional.ofNullable(profileTo.getGender())
                .map(x -> em.find(GenderEntity.class, x.getId()))
                .orElse(null));
        profileEntity.setMaritalStatus(Optional.ofNullable(profileTo.getMaritalStatus())
                .map(x -> em.find(MaritalStatusEntity.class, x.getId()))
                .orElse(null));
        profileEntity.setReligion(Optional.ofNullable(profileTo.getReligion())
                .map(x -> em.find(ReligionEntity.class, x.getId()))
                .orElse(null));
    }

    /**
     * Get profile
     *
     * @param id - profile id
     * @return - profile retrieved. if no profile is found
     * @throws EntityNotFoundException - if profile is not found
     */
    public ProfileTo getProfile(Long id) throws EntityNotFoundException {
        return profileRepository.findById(id)
                .map(p -> mapper.map(p, ProfileTo.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    /**
     * Get avatar on profile
     *
     * @param id - id of the profile in question
     * @return - profile image in base64
     */
    @Nullable
    public String getAvatar(Long id) {
        return profileRepository.findById(id)
                .map(ProfileEntity::getAvatar)
                .map(Base64Utils::encodeToString)
                .orElse(null);
    }

    /**
     * Save avatar on profile
     *
     * @param id   - id of the profile in question
     * @param file - raw image to be saved
     * @return - updated profile image in base64
     */
    public String saveAvatar(Long id, MultipartFile file) {
        try {
            byte[] scaledImage = imageUtil.scaleImage(file.getBytes(), avatarWidth, avatarHeight,
                    avatarXFactorWidth, avatarXFactorHeight);

            ProfileEntity profileEntity = profileRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            profileEntity.setAvatar(scaledImage);
            profileRepository.save(profileEntity);

            return Base64Utils.encodeToString(scaledImage);
        } catch (IOException e) {
            LOG.error("Failed to scale image!");
            throw new BusinessException("Failed to scale image!", e);
        }
    }
}
