package com.starknetworks.boundarycontext.profilemanagement.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.starknetworks.boundarycontext.masterdatamanagement.control.transferobject.*;
import com.starknetworks.boundarycontext.profilemanagement.control.transportobject.ProfileTo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class ProfileManagementRestControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    private Pattern base64Pattern;

    @Before
    public void setup() {
        base64Pattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
    }

    @Test
    public void shouldSaveUploadedFile() throws Exception {
        // given photo to upload
        File photoFile = ResourceUtils.getFile("classpath:testPhoto.jpg");

        // when photo is uploaded
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "testPhoto.png", "image/jpg", new FileInputStream(photoFile));
        MvcResult result = this.mvc.perform(multipart("/profile/{id}/image", -1L).file(multipartFile))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // upload and save successfully and return bas64 version of the image
        String content = result.getResponse().getContentAsString();
        assertThat(content).matches(base64Pattern);
    }

    @Test
    public void shouldReturnNotFoundIfProfileNotFoundToUploadImage() throws Exception {
        File photoFile = ResourceUtils.getFile("classpath:testPhoto.jpg");
        MockMultipartFile multipartFile =
                new MockMultipartFile("file", "testPhoto.png", "image/jpg", new FileInputStream(photoFile));
        this.mvc.perform(multipart("/profile/{id}/image", 0L).file(multipartFile))
                .andExpect(status().isNotFound())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void shouldSaveProfile() throws Exception {
        // given profile
        ProfileTo profile = ProfileTo.builder()
                .about("Just a regular dev")
                .birthDate(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"))
                .displayName("Joe")
                .realName("Joseph Okharedia")
                .ethnicity(new EthnicityTo(-1L, null))
                .figure(new FigureTo(-1L, null))
                .city(new CityTo(-1L, null))
                .religion(new ReligionTo(-1L, null))
                .maritalStatus(new MaritalStatusTo(-1L, null))
                .occupation("Software engineer")
                .gender(new GenderTo(-1L, null))
                .height(1.4)
                .build();

        // when saving profile, should save and return correctly
        this.mvc.perform(post("/profile")
                .content(objectMapper.writeValueAsString(profile))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.about", is("Just a regular dev")))
                .andExpect(jsonPath("$.height", is(1.4)))
                .andExpect(jsonPath("$.gender.id", is(-1)))
                .andExpect(jsonPath("$.gender.name", is("Test gender")))
                .andExpect(jsonPath("$.occupation", is("Software engineer")))
                .andExpect(jsonPath("$.maritalStatus.id", is(-1)))
                .andExpect(jsonPath("$.maritalStatus.name", is("Test marital status")))
                .andExpect(jsonPath("$.religion.id", is(-1)))
                .andExpect(jsonPath("$.religion.name", is("Test religion")))
                .andExpect(jsonPath("$.city.id", is(-1)))
                .andExpect(jsonPath("$.city.name", is("Test city")))
                .andExpect(jsonPath("$.figure.id", is(-1)))
                .andExpect(jsonPath("$.figure.name", is("Test figure")))
                .andExpect(jsonPath("$.ethnicity.id", is(-1)))
                .andExpect(jsonPath("$.ethnicity.name", is("Test ethnicity")))
                .andExpect(jsonPath("$.realName", is("Joseph Okharedia")))
                .andExpect(jsonPath("$.displayName", is("Joe")))
                // TODO: Date is problematic because of timezone differences. Need to figure this out
//                .andExpect(jsonPath("$.birthDate", is(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"))))
                .andReturn();
    }

    @Test
    public void shouldReturnBadRequestProfileIsInvalidForSaving() throws Exception {
        ProfileTo profile = ProfileTo.builder().build();
        // when saving profile, should save and return correctly
        this.mvc.perform(post("/profile")
                .content(objectMapper.writeValueAsString(profile))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    public void shouldReturnProfile() throws Exception {
        this.mvc.perform(get("/profile/-1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.about", is("Just a regular dev")))
                .andExpect(jsonPath("$.height", is(1.4)))
                .andExpect(jsonPath("$.gender.id", is(-1)))
                .andExpect(jsonPath("$.gender.name", is("Test gender")))
                .andExpect(jsonPath("$.occupation", is("Software engineer")))
                .andExpect(jsonPath("$.maritalStatus.id", is(-1)))
                .andExpect(jsonPath("$.maritalStatus.name", is("Test marital status")))
                .andExpect(jsonPath("$.religion.id", is(-1)))
                .andExpect(jsonPath("$.religion.name", is("Test religion")))
                .andExpect(jsonPath("$.city.id", is(-1)))
                .andExpect(jsonPath("$.city.name", is("Test city")))
                .andExpect(jsonPath("$.figure.id", is(-1)))
                .andExpect(jsonPath("$.figure.name", is("Test figure")))
                .andExpect(jsonPath("$.ethnicity.id", is(-1)))
                .andExpect(jsonPath("$.ethnicity.name", is("Test ethnicity")))
                .andExpect(jsonPath("$.realName", is("Joseph Okharedia")))
                .andExpect(jsonPath("$.displayName", is("Joe")))
                // TODO: Date is problematic because of timezone differences. Need to figure this out
//                .andExpect(jsonPath("$.birthDate", is(new SimpleDateFormat("yyyy-MM-dd").parse("2019-01-02"))))
                .andReturn();
    }

}
