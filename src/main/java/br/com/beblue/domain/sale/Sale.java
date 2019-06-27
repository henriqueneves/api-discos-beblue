package br.com.beblue.domain.sale;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Accessors(fluent = true)
@ToString(exclude = "sale")
@EqualsAndHashCode(exclude = "sale", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @Builder)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @NotNull
    @OrderBy("id ASC")
    private List<DiscSale> discSales = new ArrayList();
    private BigDecimal valueTotal;
    private BigDecimal cashbackTotal;
    private Date register;

    public void calculeValueTotal() {
        this.valueTotal = this.discSales.stream().map(
                n -> n.saleValue()
        ).reduce(BigDecimal::add).get();
    }

    public void registerNow(){
        this.register = new Date();
    }

}
