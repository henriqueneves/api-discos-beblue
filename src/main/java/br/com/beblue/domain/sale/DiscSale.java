package br.com.beblue.domain.sale;


import br.com.beblue.domain.disc.Disc;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@Accessors(fluent = true)
@ToString(exclude = "sale")
@EqualsAndHashCode(exclude = "sale", callSuper = false)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
public class DiscSale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(updatable = false)
    @NotNull
    private Disc disc;
    private BigDecimal saleValue;
    private BigDecimal cashbackValue;
    @ManyToOne
    @JsonIgnore
    private Sale sale;


}
