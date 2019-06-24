package br.com.beblue.ports.adapters.web;

import br.com.beblue.application.sale.SaleApplicationService;
import br.com.beblue.ports.adapters.web.error.ExceptionTranslator;
import br.com.beblue.shared.exceptions.SaleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.beblue.resources.TestsConstants.GSON;
import static br.com.beblue.resources.disc.DiscFixture.discDTOWithIdOnly;
import static br.com.beblue.resources.sale.SaleConstants.SALE_ID;
import static br.com.beblue.resources.sale.SaleFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SaleControllerTest {

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;


    @MockBean
    SaleApplicationService saleService;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        final SaleController saleController = new SaleController(saleService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(saleController)
                .setControllerAdvice(exceptionTranslator)
                .setMessageConverters(jacksonMessageConverter)
                .build();
    }

    @Test
    public void givenSaleWhenRequestToCreateThenShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/sales")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(createSaleDTO())))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(saleService).should().create(createSaleDTO());
    }

    @Test
    public void givenInvalidSaleWhenRequestToCreateThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/sales")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(invalidCreateSaleDTO())))
                .andDo(print())
                .andExpect(status().isBadRequest());

        then(saleService).should(never()).create(invalidCreateSaleDTO());
    }

    @Test
    public void givenDiscWhenRequestToEditThenShouldReturnNoContent() throws Exception {
        mockMvc.perform(put("/sales")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(saleDTO())))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(saleService).should().edit(saleDTO());
    }

    @Test
    public void givenInvalidSaleWhenRequestToEditThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(put("/sales")
                .contentType(APPLICATION_JSON_UTF8)
                .content(GSON.toJson(invalidSaleDTO())))
                .andDo(print())
                .andExpect(status().isBadRequest());

        then(saleService).should(never()).edit(invalidSaleDTO());

    }

    @Test
    public void givenIdWhenRequestToDeleteSaleThenShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/sales/" + SALE_ID)
                .contentType(APPLICATION_JSON_UTF8)
                .content(""))
                .andDo(print())
                .andExpect(status().isNoContent());

        then(saleService).should().delete(saleDTOWithIdOnly());
    }

    @Test
    public void givenInvalidIdWhenRequestToDeleteSaleThenShouldReturnBadRequest() throws Exception {
        mockMvc.perform(delete("/sales/null")
                .contentType(APPLICATION_JSON_UTF8)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());

        then(saleService).should(never()).delete(any());
    }

    @Test
    public void givenAnExistingSaleWhenFindByIdThenReturnDTO() throws Exception {
        given(saleService.findById(SALE_ID))
                .willReturn(saleDTO());

        mockMvc
                .perform(get("/sales/{id}", SALE_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..id").exists());

        then(saleService).should().findById(SALE_ID);

    }

    @Test
    public void givenANonExistentSaleWhenFindByIdThenReturnsAProblemWithErrors() throws Exception {
        given(saleService.findById(SALE_ID))
                .willThrow(new SaleNotFoundException());

        mockMvc
                .perform(get("/sales/{id}", SALE_ID).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
    }



}
