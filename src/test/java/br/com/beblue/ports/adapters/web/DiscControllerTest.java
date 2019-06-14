package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.disc.DiscService;
import br.com.beblue.ports.adapters.web.error.ExceptionTranslator;
import br.com.beblue.shared.exceptions.DiscNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.beblue.resources.TestsConstants.GSON;
import static br.com.beblue.resources.disc.DiscConstants.DISC_ID;
import static br.com.beblue.resources.disc.DiscFixture.discDTO;
import static br.com.beblue.resources.disc.DiscFixture.invalidDiscDTO;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ActiveProfiles("unit-test")
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class DiscControllerTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;


    @MockBean
    private DiscService discService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        final DiscController discController = new DiscController(discService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(discController)
                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    /* Create */

    @Test
    public void givenDiscWhenRequestToCreateDiscThenShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/discs")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(discDTO())))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(discService).should().create(discDTO());
    }

    @Test
    public void givenInvalidDiscWhenRequestToCreateDiscThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/discs")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(invalidDiscDTO())))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(discService).should(never()).create(invalidDiscDTO());
    }


    /* Find by id */

    @Test
    public void givenAnExistingDiscWhenFindByIdThenReturnDTO() throws Exception {
        given(discService.findById(DISC_ID))
                .willReturn(discDTO());

        mockMvc
                .perform(get("/discs/{id}", DISC_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists());
    }

    @Test
    public void givenANonExistentDiscWhenFindByIdThenReturnsAProblemWithErrors() throws Exception {
        given(discService.findById(DISC_ID))
                .willThrow(new DiscNotFoundException());

        mockMvc
                .perform(get("/discs/{id}", DISC_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }


}