package br.com.beblue.ports.adapters.repositories;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.disc.DiscRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

import java.util.Optional;

import static br.com.beblue.resources.disc.DiscConstants.DISC_GENRE;
import static br.com.beblue.resources.disc.DiscConstants.DISC_ID;
import static br.com.beblue.resources.disc.DiscFixture.*;
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
    public void givenAValidDiscWhenSaveThenDelegateToJpaRepository() {
        Disc disc = disc();
        discRepository.save(disc);
        then(discRepositorySpringData).should().save(disc);
    }

    @Test
    public void givenAValidDiscWhenEditThenDelegateToJpaRepository(){
        Disc disc = disc();
        discRepository.edit(disc);
        then(discRepositorySpringData).should().save(disc);
    }

    @Test
    public void givenADiscWhenDeleteThenDelegateToJpaRepository(){
        discRepository.delete(DISC_ID);
        then(discRepositorySpringData).should().deleteById(DISC_ID);
    }

    @Test
    public void givenAnExistingDiscWhenFindByIdThenDelegateToRepositoryAndReturn() {
        given(discRepositorySpringData.findById(DISC_ID)).willReturn(of(disc()));
        Optional<Disc> disc = discRepository.findById(DISC_ID);
        then(discRepositorySpringData).should().findById(DISC_ID);
        assertThat(disc).isNotEmpty();
    }

    @Test
    public void givenAnExistingGenreWhenFindByGenreThenDelegateToRepositoryAndReturn() {
        given(discRepositorySpringData.findByGenreOrderByName(DISC_GENRE, defaultFilter()))
                .willReturn(pageDisc());
        Page<Disc> page = discRepository.findByGenre(DISC_GENRE, defaultFilter());
        then(discRepositorySpringData).should().findByGenreOrderByName(DISC_GENRE, defaultFilter());
        assertThat(page).isNotEmpty();
    }
}