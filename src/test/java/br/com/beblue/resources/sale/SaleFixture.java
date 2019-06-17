package br.com.beblue.resources.sale;

import br.com.beblue.application.sale.dto.DiscSaleDTO;
import br.com.beblue.application.sale.dto.SaleDTO;
import br.com.beblue.domain.sale.DiscSale;
import br.com.beblue.domain.sale.Sale;
import br.com.beblue.shared.utils.DefaultFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static br.com.beblue.resources.disc.DiscFixture.disc;
import static br.com.beblue.resources.disc.DiscFixture.discDTO;
import static br.com.beblue.resources.sale.SaleConstants.*;
import static org.assertj.core.util.Lists.newArrayList;


public interface SaleFixture {


    static Sale sale(){
        return Sale.builder()
                .id(SALE_ID)
                .discSales(newArrayList(discSale()))
                .valueTotal(SALE_VALUE_TOTAL)
                .cashbackTotal(SALE_CASHBACK_TOTAL)
                .build();
    }

    static SaleDTO saleDTO(){
        return SaleDTO.builder()
                .id(SALE_ID)
                .discSalesDTO(newArrayList(discSaleDTO()))
                .valueTotal(SALE_VALUE_TOTAL)
                .cashbackTotal(SALE_CASHBACK_TOTAL)
                .build();
    }

    static SaleDTO invalidSaleDTO(){
        return saleDTO().discSalesDTO(null);
    }

    static SaleDTO emptyDiscSaleDTO(){
        return new SaleDTO();
    }

    static Sale saleWithIdOnly(){
        return Sale.builder()
                .id(SALE_ID)
                .build();
    }

    static SaleDTO saleDTOWithIdOnly(){
        return SaleDTO.builder()
                .id(SALE_ID)
                .build();
    }

    static Page<Sale> pageSale(){
         return new PageImpl<>(newArrayList(sale()));
    }

    static DefaultFilter defaultFilter(){
        return new DefaultFilter();
    }

    static Optional<Sale> optionalSale(){
        return Optional.of(sale());
    }

    /* DiscSale */

    static DiscSale discSale(){
        return DiscSale.builder()
                .id(DISC_SALE_ID)
                .saleValue(SALE_VALUE_TOTAL)
                .cashbackValue(SALE_CASHBACK_TOTAL)
                .disc(disc())
                .build();
    }

    static DiscSaleDTO discSaleDTO(){
        return DiscSaleDTO.builder()
                .id(DISC_SALE_ID)
                .saleValue(SALE_VALUE_TOTAL)
                .cashbackValue(SALE_CASHBACK_TOTAL)
                .discDTO(discDTO())
                .build();
    }

}
