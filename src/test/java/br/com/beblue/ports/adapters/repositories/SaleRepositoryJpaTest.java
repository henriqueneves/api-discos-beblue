package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.sale.Sale;
import br.com.beblue.domain.sale.SaleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static br.com.beblue.resources.sale.SaleConstants.SALE_ID;
import static br.com.beblue.resources.sale.SaleFixture.sale;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class SaleRepositoryJpaTest {

    @Mock
    private SaleRepositorySpringData saleRepositorySpringData;

    private SaleRepository saleRepository;

    @Before
    public void setUp() {
        saleRepository = new SaleRepositoryJpa(saleRepositorySpringData);
    }

    @Test
    public void givenAValidSaleWhenSaveThenDelegateToJpaRepository() {
        Sale sale = sale();
        saleRepository.save(sale);
        then(saleRepositorySpringData).should().save(sale);
    }

    @Test
    public void givenAValidSaleWhenEditThenDelegateToJpaRepository() {
        Sale sale = sale();
        saleRepository.edit(sale);
        then(saleRepositorySpringData).should().save(sale);
    }

    @Test
    public void givenAValidSaleWhenDeleteThenDelegateToJpaRepository() {
        Sale sale = sale();
        saleRepository.delete(sale);
        then(saleRepositorySpringData).should().delete(sale);
    }

    @Test
    public void givenAnExistingDiscWhenFindByIdThenDelegateToRepositoryAndReturn() {
        given(saleRepositorySpringData.findById(SALE_ID)).willReturn(of(sale()));
        Optional<Sale> sale = saleRepository.findById(SALE_ID);
        then(saleRepositorySpringData).should().findById(SALE_ID);
        assertThat(sale).isNotEmpty();
    }

}
