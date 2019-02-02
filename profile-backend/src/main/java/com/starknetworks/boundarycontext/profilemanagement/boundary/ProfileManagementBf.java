package com.starknetworks.boundarycontext.profilemanagement.boundary;


import com.starknetworks.boundarycontext.profilemanagement.control.ProfileBa;
import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

/**
 * Business facade to manage profiles
 */
@Component
public class ProfileManagementBf implements ProfileManagementBci {

    @Autowired
    private ProfileBa profileBa;

    /**
     * Save profile
     *
     * @param profileTo - profile to be saved
     * @return - profile version that has been saved
     */
    public ProfileTo saveProfile(ProfileTo profileTo) {
        return profileBa.saveProfile(profileTo);
    }

    /**
     * Get profile
     *
     * @param id - profile id
     * @return - profile retrieved
     * @throws EntityNotFoundException - if profile is not found
     */
    public ProfileTo getProfile(Long id) throws EntityNotFoundException {
        return profileBa.getProfile(id);
    }

    /**
     * Get avatar of profile
     *
     * @param id - Id of profile
     * @return - profile image in base64
     */
    public String getAvatar(Long id) {
        return profileBa.getAvatar(id);
    }

    /**
     * Save avatar of profile
     *
     * @param id   - Id of profile
     * @param file - avatar image file
     * @return - updated profile image in base64
     */
    public String saveAvatar(Long id, MultipartFile file) {
        return profileBa.saveAvatar(id, file);
    }
}
