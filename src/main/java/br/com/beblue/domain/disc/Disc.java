package br.com.beblue.domain.disc;

import br.com.beblue.domain.sale.DiscSale;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
@ToString(exclude = "disc")
@EqualsAndHashCode(exclude = "disc", callSuper = false)
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Genre genre;
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disc")
    private List<DiscSale> discSales = new ArrayList();

}
