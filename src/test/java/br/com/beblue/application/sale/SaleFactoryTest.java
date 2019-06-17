package br.com.beblue.application.sale;

import br.com.beblue.application.sale.dto.DiscSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.DiscSale;
import br.com.beblue.domain.sale.Sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.beblue.resources.sale.SaleFixture.*;
import static br.com.beblue.resources.sale.SaleFixture.sale;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SaleFactoryTest {

    @Test
    public void givenASaleWhenCastToSaleDTOThenGenerateValidObject() {
        Sale sale = sale();
        SaleDTO saleDTO = SaleFactory.createSaleDTO(sale);

        assertThat(sale.id()).isEqualTo(saleDTO.id());
        assertThat(sale.register()).isEqualTo(saleDTO.register());
        assertThat(sale.valueTotal()).isEqualTo(saleDTO.valueTotal());
        assertThat(sale.cashbackTotal()).isEqualTo(saleDTO.cashbackTotal());
    }

    @Test
    public void givenASaleDTOWhenCastToSaleThenGenerateValidObject() {
        SaleDTO saleDTO = saleDTO();
        Sale sale = SaleFactory.createSale(saleDTO);

        assertThat(saleDTO.id()).isEqualTo(sale.id());
        assertThat(saleDTO.register()).isEqualTo(sale.register());
        assertThat(saleDTO.valueTotal()).isEqualTo(sale.valueTotal());
        assertThat(saleDTO.cashbackTotal()).isEqualTo(sale.cashbackTotal());
    }

    @Test
    public void givenADiscSaleWhenCastToDiscSalesDTOThenGenerateValidObject() {
        DiscSale discSale = discSale();
        DiscSaleDTO discSaleDTO = SaleFactory.createDiscSaleDTO(discSale);

        assertThat(discSale.id()).isEqualTo(discSaleDTO.id());
        assertThat(discSale.saleValue()).isEqualTo(discSaleDTO.saleValue());
        assertThat(discSale.cashbackValue()).isEqualTo(discSaleDTO.cashbackValue());
    }

    @Test
    public void givenADiscSaleDTOWhenCastToDiscSalesThenGenerateValidObject() {
        DiscSaleDTO discSaleDTO = discSaleDTO();
        DiscSale discSale = SaleFactory.createDiscSale(discSaleDTO);

        assertThat(discSale.id()).isEqualTo(discSaleDTO.id());
        assertThat(discSale.saleValue()).isEqualTo(discSaleDTO.saleValue());
        assertThat(discSale.cashbackValue()).isEqualTo(discSaleDTO.cashbackValue());
    }
}
