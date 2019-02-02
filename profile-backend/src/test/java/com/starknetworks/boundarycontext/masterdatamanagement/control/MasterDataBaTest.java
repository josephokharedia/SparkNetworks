package com.starknetworks.boundarycontext.masterdatamanagement.control;

import com.starknetworks.boundarycontext.masterdatamanagement.integration.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MasterDataBa.class)
public class MasterDataBaTest {

    @Autowired
    MasterDataBa masterDataBa;

    @MockBean
    CityRepository cityRepository;
    @MockBean
    EthnicityRepository ethnicityRepository;
    @MockBean
    FigureRepository figureRepository;
    @MockBean
    GenderRepository genderRepository;
    @MockBean
    MaritalStatusRepository maritalStatusRepository;
    @MockBean
    ReligionRepository religionRepository;

    @Test
    public void testFindAllCities() {
        // when find all cities
        masterDataBa.getAllCities();

        // test that find all is called
        verify(cityRepository).findAll();
    }

    @Test
    public void testFindAllEthnicity() {
        // when find all ethnicity
        masterDataBa.getAllEthnicity();

        // test that find all is called
        verify(ethnicityRepository).findAll();
    }

    @Test
    public void testFindAllFigures() {
        // when find all figures
        masterDataBa.getAllFigures();

        // test that find all is called
        verify(figureRepository).findAll();
    }

    @Test
    public void testFindAllGenders() {
        // when find all genders
        masterDataBa.getAllGenders();

        // test that find all is called
        verify(genderRepository).findAll();
    }

    @Test
    public void testFindAllMaritalStatuses() {
        // when find all marital statuses
        masterDataBa.getAllMaritalStatuses();

        // test that find all is called
        verify(maritalStatusRepository).findAll();
    }

    @Test
    public void testFindAllReligions() {
        // when find all religions
        masterDataBa.getAllReligions();

        // test that find all is called
        verify(religionRepository).findAll();
    }

    @Test
    public void testFindAllCitiesContainingName() {
        // when find all religions
        masterDataBa.findCitiesByName("");

        // test that find all is called
        verify(cityRepository).findByNameContainingIgnoreCase("");
    }

    @Test
    public void shouldFindCityById() {
        // when find city by id
        masterDataBa.getCity(-1L);

        // findById is called on repository
        verify(cityRepository).findById(-1L);
    }

    @Test
    public void shouldFindMaritalStatusById() {
        // when find marital status by id
        masterDataBa.getMaritalStatus(-1L);

        // findById is called on repository
        verify(maritalStatusRepository).findById(-1L);
    }

    @Test
    public void shouldFindFigureById() {
        // when find figure by id
        masterDataBa.getFigure(-1L);

        // findById is called on repository
        verify(figureRepository).findById(-1L);
    }

    @Test
    public void shouldFindGenderById() {
        // when find gender by id
        masterDataBa.getGender(-1L);

        // findById is called on repository
        verify(genderRepository).findById(-1L);
    }

    @Test
    public void shouldFindReligionById() {
        // when find religion by id
        masterDataBa.getReligion(-1L);

        // findById is called on repository
        verify(religionRepository).findById(-1L);
    }

    @Test
    public void shouldFindEthnicityById() {
        // when find ethnicity by id
        masterDataBa.getEthnicity(-1L);

        // findById is called on repository
        verify(ethnicityRepository).findById(-1L);
    }
}
