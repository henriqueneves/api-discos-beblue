package br.com.beblue.domain.sale;

import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Accessors(fluent = true)
@ToString(exclude = "sale")
@EqualsAndHashCode(exclude = "sale", callSuper = false)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sale")
    @NotNull
    private List<DiscSale> discSales = new ArrayList();
    private BigDecimal valueTotal;
    private BigDecimal cashbackTotal;
    private Date register;


}
