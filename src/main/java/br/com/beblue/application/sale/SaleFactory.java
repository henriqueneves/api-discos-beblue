package br.com.beblue.application.sale;

import br.com.beblue.application.disc.DiscFactory;
import br.com.beblue.application.sale.dto.*;
import br.com.beblue.domain.sale.DiscSale;
import br.com.beblue.domain.sale.Sale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SaleFactory {

    public static SaleDTO createSaleDTO (Sale sale) {
        return SaleDTO
                .builder()
                .id(sale.id())
                .discSalesDTO(createListDiscSaleDTO(sale.discSales()))
                .valueTotal(sale.valueTotal())
                .cashbackTotal(sale.cashbackTotal())
                .register(sale.register())
                .build();
    }

    public static Sale createSale(SaleDTO saleDTO){
        return Sale
                .builder()
                .id(saleDTO.id())
                .discSales(createListDiscSale(saleDTO.discSalesDTO()))
                .valueTotal(saleDTO.valueTotal())
                .cashbackTotal(saleDTO.cashbackTotal())
                .register(saleDTO.register())
                .build();
    }

    public static Sale createSale(CreateSaleDTO createSaleDTO){
        return Sale
                .builder()
                .discSales(createListDiscSale(createSaleDTO.discsID()))
                .build();
    }

    public static List<DiscSale> createListDiscSale(List<DiscSaleDTO> discSaleDTO){
        return discSaleDTO == null ? new ArrayList() : discSaleDTO.stream().map(SaleFactory::createDiscSale).collect(toList());
    }

    public static List<DiscSale> createListDiscSale(Long[]  discsId){
        List<Long> listDiscsId = Arrays.asList(discsId);
        return discsId.length == 0 ? new ArrayList() : listDiscsId.stream().map(SaleFactory::createDiscSale).collect(toList());
    }

    public static List<DiscSaleDTO> createListDiscSaleDTO(List<DiscSale> discSale){
        return discSale == null ? new ArrayList() : discSale.stream().map(SaleFactory::createDiscSaleDTO).collect(toList());
    }

    public static DiscSale createDiscSale(DiscSaleDTO discSaleDTO){
        return DiscSale
                .builder()
                .id(discSaleDTO.id())
                .disc(DiscFactory.createDisc(discSaleDTO.discDTO()))
                .saleValue(discSaleDTO.saleValue())
                .cashbackValue(discSaleDTO.cashbackValue())
                .build();
    }

    public static DiscSale createDiscSale(Long discSaleId){
        return DiscSale
                .builder()
                .id(discSaleId)
                .build();
    }

    public static DiscSaleDTO createDiscSaleDTO(DiscSale discSale){
        return DiscSaleDTO
                .builder()
                .id(discSale.id())
                .discDTO(DiscFactory.createDiscDTO(discSale.disc()))
                .saleValue(discSale.saleValue())
                .cashbackValue(discSale.cashbackValue())
                .build();

    }

}
