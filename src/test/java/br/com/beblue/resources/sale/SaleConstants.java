package br.com.beblue.resources.sale;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.Genre;
import br.com.beblue.resources.disc.DiscFixture;

import java.math.BigDecimal;
import java.util.Date;

public class SaleConstants {

    public static final Long SALE_ID = 1L;
    public static final BigDecimal SALE_VALUE_TOTAL = new BigDecimal("22.50");
    public static final BigDecimal SALE_CASHBACK_TOTAL = new BigDecimal("2.50");

    public static final Long DISC_SALE_ID = 1L;
    public static final Disc DISC_SALE_DISC = DiscFixture.disc();
    public static final BigDecimal DISC_SALE_SALE_VALUE = new BigDecimal("22.50");
    public static final BigDecimal DISC_SALE_CASHBACK_VALUE = new BigDecimal("2.50");

    public static final Long[] CREATE_DISC_SALE_ID = {SALE_ID};

    public static final Date MAX_DATE = new Date(Long.MAX_VALUE);
    public static  final Date MIN_DATE = new Date(Long.MIN_VALUE);

}
