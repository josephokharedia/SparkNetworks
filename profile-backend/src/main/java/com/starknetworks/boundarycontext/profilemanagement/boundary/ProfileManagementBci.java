package com.starknetworks.boundarycontext.profilemanagement.boundary;

import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

/**
 * Business component interface to manage profiles
 */
public interface ProfileManagementBci {

    /**
     * Save profile
     *
     * @param profileTo - profile to be saved
     * @return - profile version that has been saved
     */
    ProfileTo saveProfile(ProfileTo profileTo);

    /**
     * Get profile
     *
     * @param id - profile id
     * @return - profile retrieved
     * @throws EntityNotFoundException - if profile is not found
     */
    ProfileTo getProfile(Long id) throws EntityNotFoundException;

    /**
     * Get avatar of profile
     *
     * @param id   - Id of profile
     * @return - profile image in base64
     */
    String getAvatar(Long id);

    /**
     * Save avatar of profile
     *
     * @param id   - Id of profile
     * @param file - avatar image file
     * @return - updated profile image in base64
     */
    String saveAvatar(Long id, MultipartFile file);
}
