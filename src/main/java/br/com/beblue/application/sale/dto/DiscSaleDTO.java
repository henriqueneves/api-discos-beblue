package br.com.beblue.application.sale.dto;

import br.com.beblue.application.disc.dto.DiscDTO;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Data
@Builder
@ToString
@Accessors(fluent = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
@JsonAutoDetect(fieldVisibility = ANY)

public class DiscSaleDTO {

    private Long id;
    private DiscDTO discDTO;
    private BigDecimal saleValue;
    private BigDecimal cashbackValue;
}
