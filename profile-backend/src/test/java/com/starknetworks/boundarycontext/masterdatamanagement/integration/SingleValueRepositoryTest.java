package com.starknetworks.boundarycontext.masterdatamanagement.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleValueRepositoryTest {

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

    @Test
    public void testFindCityById() {
        assert cityRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllCities() {
        assert !cityRepository.findAll().isEmpty();
    }

    @Test
    public void testFindEthnicityById() {
        assert ethnicityRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllEthnicity() {
        assert !ethnicityRepository.findAll().isEmpty();
    }

    @Test
    public void testFindFigureById() {
        assert figureRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllFigures() {
        assert !figureRepository.findAll().isEmpty();
    }

    @Test
    public void testFindGenderById() {
        assert genderRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllGenders() {
        assert !genderRepository.findAll().isEmpty();
    }

    @Test
    public void testFindMaritalStatusById() {
        assert maritalStatusRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllMaritalStatuses() {
        assert !maritalStatusRepository.findAll().isEmpty();
    }

    @Test
    public void testFindReligionById() {
        assert religionRepository.findById(-1L).isPresent();
    }

    @Test
    public void testFindAllReligions() {
        assert !religionRepository.findAll().isEmpty();
    }

    @Test
    public void testFindCitiesByName() {
        assertThat(cityRepository.findByNameContainingIgnoreCase("another")).hasSize(2);
    }
}
