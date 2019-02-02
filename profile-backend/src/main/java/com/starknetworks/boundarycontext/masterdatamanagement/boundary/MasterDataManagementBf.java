package com.starknetworks.boundarycontext.masterdatamanagement.boundary;

import com.starknetworks.boundarycontext.masterdatamanagement.control.MasterDataBa;
import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Business facade for getting master data
 */
@Component
public class MasterDataManagementBf implements MasterDataManagementBci {

    @Autowired
    private MasterDataBa masterDataBa;

    /**
     * Get all cities
     *
     * @return all cities
     */
    @Override
    public List<CityTo> getAllCities() {
        return masterDataBa.getAllCities();
    }

    /**
     * Get all cities by name
     *
     * @return all cities by name
     */
    @Override
    public List<CityTo> findCitiesByName(String name) {
        return masterDataBa.findCitiesByName(name);
    }

    /**
     * Get city by id
     *
     * @param id - id of city to lookup
     * @return city
     */
    @Override
    public Optional<CityTo> getCity(Long id) {
        return masterDataBa.getCity(id);
    }

    /**
     * Get all ethnicity
     *
     * @return all ethnicity
     */
    @Override
    public List<EthnicityTo> getAllEthnicity() {
        return masterDataBa.getAllEthnicity();
    }

    /**
     * Get ethnicity by id
     *
     * @param id - id of ethnicity to lookup
     * @return ethnicity
     */
    @Override
    public Optional<EthnicityTo> getEthnicity(Long id) {
        return masterDataBa.getEthnicity(id);
    }

    /**
     * Get all figures
     *
     * @return all figures
     */
    @Override
    public List<FigureTo> getAllFigures() {
        return masterDataBa.getAllFigures();
    }

    /**
     * Get figure by id
     *
     * @param id - id of figure to lookup
     * @return figure
     */
    @Override
    public Optional<FigureTo> getFigure(Long id) {
        return masterDataBa.getFigure(id);
    }

    /**
     * Get all genders
     *
     * @return all genders
     */
    @Override
    public List<GenderTo> getAllGenders() {
        return masterDataBa.getAllGenders();
    }

    /**
     * Get gender by id
     *
     * @param id - id of gender to lookup
     * @return gender
     */
    @Override
    public Optional<GenderTo> getGender(Long id) {
        return masterDataBa.getGender(id);
    }

    /**
     * Get all marital statuses
     *
     * @return all marital statuses
     */
    @Override
    public List<MaritalStatusTo> getAllMaritalStatuses() {
        return masterDataBa.getAllMaritalStatuses();
    }

    /**
     * Get martial status by id
     *
     * @param id - id of marital status to lookup
     * @return marital status
     */
    @Override
    public Optional<MaritalStatusTo> getMaritalStatus(Long id) {
        return masterDataBa.getMaritalStatus(id);
    }

    /**
     * Get all religions
     *
     * @return all religions
     */
    @Override
    public List<ReligionTo> getAllReligions() {
        return masterDataBa.getAllReligions();
    }

    /**
     * Get religion by id
     *
     * @param id - id of religion to lookup
     * @return religion
     */
    @Override
    public Optional<ReligionTo> getReligion(Long id) {
        return masterDataBa.getReligion(id);
    }
}
