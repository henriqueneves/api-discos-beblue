package br.com.beblue.application.disc.dto;

import br.com.beblue.domain.disc.Genre;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Data
@ToString
@Builder(toBuilder = true)
@Accessors(fluent = true)
@NoArgsConstructor(force = true)
@AllArgsConstructor(onConstructor_ = @Builder)
@JsonAutoDetect(fieldVisibility = ANY)
public class DiscDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Genre genre;
    @Positive
    private BigDecimal price;

}
