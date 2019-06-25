package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.CreateSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.SaleRepository;
import br.com.beblue.ports.adapters.messaging.CashbackProducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.beblue.resources.disc.DiscFixture.defaultFilter;
import static br.com.beblue.resources.sale.SaleConstants.SALE_ID;
import static br.com.beblue.resources.sale.SaleFixture.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class SaleApplicationServiceTest {

    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private CashbackProducer cashbackProducer;

    @Before
    public void setUp() {
        saleService = new SaleApplicationService(saleRepository, cashbackProducer);
    }

    @Test
    public void givenASaleDTOWhenRequestToCreateDiscThenInvokeRepositoryCreate(){
        CreateSaleDTO createSaleDTO = createSaleDTO();
        given(saleRepository.save(any()))
                .willReturn(sale());
        saleService.create(createSaleDTO);
        then(saleRepository).should().save(any());
    }

    @Test
    public void givenASaleDTOWhenRequestToEditSaleThenInvokeRepositoryEdit(){
        SaleDTO saleDTO = saleDTO();
        saleService.edit(saleDTO);
        then(saleRepository).should().edit(sale());
    }

    @Test
    public void givenASaleDTOWhenRequestToDeleteSaleThenInvokeRepositoryDelete(){
        SaleDTO saleDTO = saleDTO();
        saleService.delete(saleDTO);
        then(saleRepository).should().delete(sale());
    }

    @Test
    public void givenAIdWhenRequestToFindByIdThenInvokeRepository(){
        given(saleRepository.findById(SALE_ID))
                .willReturn(optionalSale());
        saleService.findById(SALE_ID);
        then(saleRepository).should().findById(SALE_ID);
    }

    @Test
    public void givenAGenreAndPageableWhenRequestToSearchThenInvokeRepository(){
        given(saleRepository.searchByDate(null, null, defaultFilter()))
                .willReturn(pageSale());
        saleService.searchByDate(null, null, defaultFilter());
        then(saleRepository).should().searchByDate(null, null, defaultFilter());
    }

}
