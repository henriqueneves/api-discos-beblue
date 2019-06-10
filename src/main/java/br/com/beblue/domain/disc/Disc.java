package br.com.beblue.domain.disc;

import br.com.beblue.domain.genre.Genre;
import br.com.beblue.domain.sale.DiscSale;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Accessors(fluent = true)
@ToString(exclude = "disc")
@EqualsAndHashCode(exclude = "disc", callSuper = false)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
@Entity
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(updatable = false)
    private Genre genre;
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disc")
    @JsonIgnore
    private List<DiscSale> discSales = new ArrayList();

}
