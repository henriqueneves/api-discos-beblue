package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import org.junit.Test;
import static br.com.beblue.resources.disc.DiscFixture.disc;
import static br.com.beblue.resources.disc.DiscFixture.discDTO;
import static org.assertj.core.api.Assertions.*;

public class DiscFactoryTest {

    @Test
    public void givenADiscWhenCastToDiscDTOThenGenerateValidObject() {
        Disc disc = disc();
        DiscDTO discDTO = DiscFactory.createDiscDTO(disc);

        assertThat(disc.id()).isEqualTo(discDTO.id());
        assertThat(disc.name()).isEqualTo(discDTO.name());
        assertThat(disc.genre()).isEqualTo(discDTO.genre());
        assertThat(disc.price()).isEqualTo(discDTO.price());
    }

    @Test
    public void givenDiscDTOWhenCastToDiscThenGenerateValidObject() {
        DiscDTO discDTO = discDTO();
        Disc disc = DiscFactory.createDisc(discDTO);

        assertThat(discDTO.id()).isEqualTo(disc.id());
        assertThat(discDTO.name()).isEqualTo(disc.name());
        assertThat(discDTO.genre()).isEqualTo(disc.genre());
        assertThat(discDTO.price()).isEqualTo(disc.price());
    }
}