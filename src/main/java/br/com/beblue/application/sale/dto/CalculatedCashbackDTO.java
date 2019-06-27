package br.com.beblue.application.sale.dto;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.sale.DiscSale;
import br.com.beblue.domain.sale.Sale;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.OrderBy;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Data
@Builder
@ToString
@Accessors(fluent = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
@JsonAutoDetect(fieldVisibility = ANY)
public class CalculatedCashbackDTO {

    @NotNull
    private Long idSale;
    @NotEmpty
    private List<DiscsCalculatedCashbackDTO> discs;
    @NotNull
    private BigDecimal cashbackTotal;

}
