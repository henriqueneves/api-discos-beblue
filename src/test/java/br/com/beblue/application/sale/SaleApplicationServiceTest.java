package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.SaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static br.com.beblue.resources.sale.SaleConstants.SALE_ID;
import static br.com.beblue.resources.sale.SaleFixture.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class SaleApplicationServiceTest {

    private SaleService saleService;

    @Mock
    private SaleRepository saleRepository;

    @Before
    public void setUp() {
        saleService = new SaleApplicationService(saleRepository);
    }

    /* Create */

    @Test
    public void givenASaleDTOWhenRequestToCreateDiscThenInvokeRepositoryCreate(){
        SaleDTO saleDTO = saleDTO();
        saleService.create(saleDTO);
        then(saleRepository).should().save(sale());
    }

    /* Edit */

    @Test
    public void givenASaleDTOWhenRequestToEditSaleThenInvokeRepositoryEdit(){
        SaleDTO saleDTO = saleDTO();
        saleService.edit(saleDTO);
        then(saleRepository).should().edit(sale());
    }

    /* Delete */

    @Test
    public void givenASaleDTOWhenRequestToDeleteSaleThenInvokeRepositoryDelete(){
        SaleDTO saleDTO = saleDTO();
        saleService.delete(saleDTO);
        then(saleRepository).should().delete(sale());
    }

    /* findById */
    @Test
    public void givenAIdWhenRequestToFindByIdThenInvokeRepository(){
        given(saleRepository.findById(SALE_ID))
                .willReturn(optionalSale());
        saleService.findById(SALE_ID);
        then(saleRepository).should().findById(SALE_ID);
    }

}