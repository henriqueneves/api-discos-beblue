package br.com.beblue.application.sale.dto;

import br.com.beblue.domain.sale.DiscSale;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Data
@Builder
@ToString
@Accessors(fluent = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
@JsonAutoDetect(fieldVisibility = ANY)
public class SaleDTO {

    private Long id;
    private List<DiscSaleDTO> discSalesDTO = new ArrayList();
    private BigDecimal valueTotal;
    private BigDecimal cashbackTotal;
    private Date register;
}
