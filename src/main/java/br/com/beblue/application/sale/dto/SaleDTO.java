package br.com.beblue.application.sale.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.*;

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
    @NotNull
    private List<DiscSaleDTO> discSalesDTO;
    @Positive
    private BigDecimal valueTotal;
    @Positive
    private BigDecimal cashbackTotal;
    private Date register;
}
