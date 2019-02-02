package com.starknetworks.boundarycontext.masterdatamanagement.boundary;

import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;

import java.util.List;
import java.util.Optional;

/**
 * Business component interface to get master data
 */
public interface MasterDataManagementBci {
    /**
     * Get all cities
     *
     * @return all cities
     */
    List<CityTo> getAllCities();

    /**
     * Get all cities by name
     *
     * @return all cities by name
     */
    List<CityTo> findCitiesByName(String name);

    /**
     * Get city by id
     *
     * @param id - id of city to lookup
     * @return city
     */
    Optional<CityTo> getCity(Long id);

    /**
     * Get all ethnicity
     *
     * @return all ethnicity
     */
    List<EthnicityTo> getAllEthnicity();

    /**
     * Get ethnicity by id
     *
     * @param id - id of ethnicity to lookup
     * @return ethnicity
     */
    Optional<EthnicityTo> getEthnicity(Long id);

    /**
     * Get all figures
     *
     * @return all figures
     */
    List<FigureTo> getAllFigures();

    /**
     * Get figure by id
     *
     * @param id - id of figure to lookup
     * @return figure
     */
    Optional<FigureTo> getFigure(Long id);

    /**
     * Get all genders
     *
     * @return all genders
     */
    List<GenderTo> getAllGenders();

    /**
     * Get gender by id
     *
     * @param id - id of gender to lookup
     * @return gender
     */
    Optional<GenderTo> getGender(Long id);

    /**
     * Get all marital statuses
     *
     * @return all marital statuses
     */
    List<MaritalStatusTo> getAllMaritalStatuses();

    /**
     * Get marital status by id
     *
     * @param id - id of marital status to lookup
     * @return marital status
     */
    Optional<MaritalStatusTo> getMaritalStatus(Long id);

    /**
     * Get all religions
     *
     * @return all religions
     */
    List<ReligionTo> getAllReligions();

    /**
     * Get religion by id
     *
     * @param id - id of religion to lookup
     * @return religion
     */
    Optional<ReligionTo> getReligion(Long id);
}
