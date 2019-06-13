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

import static br.com.beblue.resources.TestsConstants.DISC_ID;
import static br.com.beblue.resources.TestsFixture.discDTO;
import static org.mockito.BDDMockito.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private MockMvc restMockMvc;

    @Before
    public void setUp(){
        final DiscController discController = new DiscController(discService);
        this.restMockMvc = MockMvcBuilders.standaloneSetup(discController)
                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    public void givenAnExistingDiscWhenFindByIdThenReturnDTO() throws Exception {
        given(discService.findById(DISC_ID))
                .willReturn(discDTO());

        restMockMvc
                .perform(get("/discs/{id}", DISC_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists());
    }

    @Test
    public void givenANonExistentDiscWhenFindByIdThenReturnsAProblemWithErros() throws Exception {
        given(discService.findById(DISC_ID))
                .willThrow(new DiscNotFoundException());

        restMockMvc
                .perform(get("/discs/{id}", DISC_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }


}