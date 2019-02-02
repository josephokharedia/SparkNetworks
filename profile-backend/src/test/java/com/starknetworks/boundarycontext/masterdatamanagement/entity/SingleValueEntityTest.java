package com.starknetworks.boundarycontext.masterdatamanagement.entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SingleValueEntityTest {

    @Autowired
    private TestEntityManager em;

    @Test
    public void testFindCityById() {
        assertThat(em.find(CityEntity.class, -1L)).isNotNull();
    }

    @Test
    public void testFindEthnicityById() {
        assertThat(em.find(EthnicityEntity.class, -1L)).isNotNull();
    }

    @Test
    public void testFindFigureById() {
        assertThat(em.find(FigureEntity.class, -1L)).isNotNull();
    }

    @Test
    public void testFindGenderById() {
        assertThat(em.find(GenderEntity.class, -1L)).isNotNull();
    }

    @Test
    public void testFindMaritalStatusById() {
        assertThat(em.find(MaritalStatusEntity.class, -1L)).isNotNull();
    }

    @Test
    public void testFindReligionById() {
        assertThat(em.find(ReligionEntity.class, -1L)).isNotNull();
    }
}
