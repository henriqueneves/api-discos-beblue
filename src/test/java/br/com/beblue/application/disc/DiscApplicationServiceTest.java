package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.DiscRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.beblue.resources.disc.DiscConstants.DISC_GENRE;
import static br.com.beblue.resources.disc.DiscConstants.DISC_ID;
import static br.com.beblue.resources.disc.DiscFixture.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class DiscApplicationServiceTest {

    private DiscService discService;

    @Mock
    private DiscRepository discRepository;

    @Before
    public void setUp() {
        discService = new DiscApplicationService(discRepository);
    }

    @Test
    public void givenADiscDTOWhenRequestToCreateDiscThenInvokeRepositoryCreate(){
        DiscDTO discDTO = discDTO();
        discService.create(discDTO);
        then(discRepository).should().save(disc());
    }

    @Test
    public void givenADiscDTOWhenRequestToEditDiscThenInvokeRepositoryEdit(){
        DiscDTO discDTO = discDTO();
        discService.edit(discDTO);
        then(discRepository).should().edit(disc());
    }

    @Test
    public void givenADiscDTOWhenRequestToDeleteDiscThenInvokeRepositoryDelete(){
        discService.delete(DISC_ID);
        then(discRepository).should().delete(DISC_ID);
    }

    @Test
    public void givenAIdWhenRequestToFindByIdThenInvokeRepository(){
        given(discRepository.findById(DISC_ID))
                .willReturn(optionalDisc());
        discService.findById(DISC_ID);
        then(discRepository).should().findById(DISC_ID);
    }

    @Test
    public void givenAGenreAndPageableWhenRequestToSearchThenInvokeRepository(){
        given(discRepository.findByGenre(DISC_GENRE, defaultFilter()))
                .willReturn(pageDisc());
        discService.findByGenreOrderByName(DISC_GENRE, defaultFilter());
        then(discRepository).should().findByGenre(DISC_GENRE, defaultFilter());
    }

}