package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import br.com.beblue.resources.TestsFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static br.com.beblue.resources.TestsConstants.DISC_ID;
import static br.com.beblue.resources.TestsFixture.*;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class DiscRepositoryJpaTest {

    @Mock
    private DiscRepositorySpringData discRepositorySpringData;

    private DiscRepository discRepository;

    @Before
    public void setUp() {
        discRepository = new DiscRepositoryJpa(discRepositorySpringData);
    }

    @Test
    public void givenAValidDiscWhenSaveThenDelegateToRepository() {
        //given
        Disc disc = disc();
        //when
        discRepository.save(disc);
        //then
        then(discRepositorySpringData).should().save(disc);
    }

    @Test
    public void givenAnExistingDiscWhenFindByIdThenDelegateToRepositoryAndReturn() {
        //given
        given(discRepositorySpringData.findById(DISC_ID)).willReturn(of(disc()));
        //when
        Optional<Disc> disc = discRepository.findById(DISC_ID);
        //then
        then(discRepositorySpringData).should().findById(DISC_ID);
        assertThat(disc).isNotEmpty();
    }
}