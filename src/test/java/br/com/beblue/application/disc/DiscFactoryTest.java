package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import static br.com.beblue.resources.TestsFixture.disc;
import static br.com.beblue.resources.TestsFixture.discDTO;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class DiscFactoryTest {

    @Test
    public void givenADiscWhenCastToDiscDTOThenGenerateValidObject() {
        //given
        Disc disc = disc();
        //when
        DiscDTO discDTO = DiscFactory.createDiscDTO(disc);
        //then
        assertThat(disc.id()).isEqualTo(discDTO.id());
        assertThat(disc.name()).isEqualTo(discDTO.name());
        assertThat(disc.genre()).isEqualTo(discDTO.genre());
        assertThat(disc.price()).isEqualTo(discDTO.price());
    }

    @Test
    public void givenDiscDTOWhenCastToDiscThenGenerateValidObject() {
        //given
        DiscDTO discDTO = discDTO();
        //when
        Disc disc = DiscFactory.createDisc(discDTO);
        //then
        assertThat(discDTO.id()).isEqualTo(disc.id());
        assertThat(discDTO.name()).isEqualTo(disc.name());
        assertThat(discDTO.genre()).isEqualTo(disc.genre());
        assertThat(discDTO.price()).isEqualTo(disc.price());
    }
}