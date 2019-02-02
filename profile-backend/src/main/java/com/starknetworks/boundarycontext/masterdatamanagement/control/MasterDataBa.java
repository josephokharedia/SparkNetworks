package com.starknetworks.boundarycontext.masterdatamanagement.control;

import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;
import com.starknetworks.boundarycontext.masterdatamanagement.integration.*;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Search master data
 */
@Component
public class MasterDataBa {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private EthnicityRepository ethnicityRepository;
    @Autowired
    private FigureRepository figureRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    private ReligionRepository religionRepository;

    private DozerBeanMapper mapper = new DozerBeanMapper();

    /**
     * Get all cities
     *
     * @return all cities
     */
    public List<CityTo> getAllCities() {
        return cityRepository.findAll().stream().map(x -> mapper.map(x, CityTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all cities by name
     *
     * @return all cities by name
     */
    public List<CityTo> findCitiesByName(String name) {
        return cityRepository.findByNameContainingIgnoreCase(name).stream().
                map(x -> mapper.map(x, CityTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all ethnicity
     *
     * @return all ethnicity
     */
    public List<EthnicityTo> getAllEthnicity() {
        return ethnicityRepository.findAll().stream().map(x -> mapper.map(x, EthnicityTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all figures
     *
     * @return all figures
     */
    public List<FigureTo> getAllFigures() {
        return figureRepository.findAll().stream().map(x -> mapper.map(x, FigureTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all genders
     *
     * @return all genders
     */
    public List<GenderTo> getAllGenders() {
        return genderRepository.findAll().stream().map(x -> mapper.map(x, GenderTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all marital statuses
     *
     * @return all marital statuses
     */
    public List<MaritalStatusTo> getAllMaritalStatuses() {
        return maritalStatusRepository.findAll().stream().map(x -> mapper.map(x, MaritalStatusTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get all religions
     *
     * @return all religions
     */
    public List<ReligionTo> getAllReligions() {
        return religionRepository.findAll().stream().map(x -> mapper.map(x, ReligionTo.class))
                .collect(Collectors.toList());
    }

    /**
     * Get city by id
     *
     * @param id - id of city to lookup
     * @return city
     */
    public Optional<CityTo> getCity(Long id) {
        return cityRepository.findById(id).map(x -> mapper.map(x, CityTo.class));
    }

    /**
     * Get religion by id
     *
     * @param id - id of religion to lookup
     * @return religion
     */
    public Optional<ReligionTo> getReligion(Long id) {
        return religionRepository.findById(id).map(x -> mapper.map(x, ReligionTo.class));
    }

    /**
     * Get marital status by id
     *
     * @param id - id of marital status to lookup
     * @return marital status
     */
    public Optional<MaritalStatusTo> getMaritalStatus(Long id) {
        return maritalStatusRepository.findById(id).map(x -> mapper.map(x, MaritalStatusTo.class));
    }

    /**
     * Get ethnicity by id
     *
     * @param id - id of ethnicity to lookup
     * @return ethnicity
     */
    public Optional<EthnicityTo> getEthnicity(Long id) {
        return ethnicityRepository.findById(id).map(x -> mapper.map(x, EthnicityTo.class));
    }

    /**
     * Get gender by id
     *
     * @param id - id of gender to lookup
     * @return gender
     */
    public Optional<GenderTo> getGender(Long id) {
        return genderRepository.findById(id).map(x -> mapper.map(x, GenderTo.class));
    }

    /**
     * Get figure by id
     *
     * @param id - id of figure to lookup
     * @return figure
     */
    public Optional<FigureTo> getFigure(Long id) {
        return figureRepository.findById(id).map(x -> mapper.map(x, FigureTo.class));
    }
}
