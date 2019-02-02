package com.starknetworks.boundarycontext.masterdatamanagement.boundary;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class MasterDataRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldGetCities() throws Exception {
        this.mvc.perform(get("/masterdata/city")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void shouldGetEthnicity() throws Exception {
        this.mvc.perform(get("/masterdata/ethnicity")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test ethnicity")));
    }

    @Test
    public void shouldGetReligions() throws Exception {
        this.mvc.perform(get("/masterdata/religion")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test religion")));
    }

    @Test
    public void shouldGetGenders() throws Exception {
        this.mvc.perform(get("/masterdata/gender")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test gender")));
    }

    @Test
    public void shouldGetFigures() throws Exception {
        this.mvc.perform(get("/masterdata/figure")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test figure")));
    }

    @Test
    public void shouldGetMaritalStatuses() throws Exception {
        this.mvc.perform(get("/masterdata/maritalStatus")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test marital status")));
    }

    @Test
    public void shouldGetCitiesByName() throws Exception {
        this.mvc.perform(get(String.format("/masterdata/city/%s", "Test"))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", is(-1)))
                .andExpect(jsonPath("$.[0].name", is("Test city")));
    }

}
