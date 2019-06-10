package br.com.beblue.application.disc.dto;

import br.com.beblue.domain.genre.Genre;
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
public class DiscDTO {

    private Long id;
    private String name;
    private Genre genre;
    private BigDecimal price;
}
