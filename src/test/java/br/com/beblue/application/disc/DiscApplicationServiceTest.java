package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.DiscRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static br.com.beblue.resources.disc.DiscFixture.disc;
import static br.com.beblue.resources.disc.DiscFixture.discDTO;
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

    /* Create */

    @Test
    public void givenADiscDTOWhenRequestToCreateDiscThenInvokeRepositoryCreate(){
        DiscDTO discDTO = discDTO();
        discService.create(discDTO);
        then(discRepository).should().save(disc());
    }

    /* Edit */

    @Test
    public void givenADiscDTOWhenRequestToEditDiscThenInvokeRepositoryEdit(){
        DiscDTO discDTO = discDTO();
        discService.edit(discDTO);
        then(discRepository).should().edit(disc());
    }

    /* Delete */

    @Test
    public void givenADiscDTOWhenRequestToDeleteDiscThenInvokeRepositoryDelete(){
        DiscDTO discDTO = discDTO();
        discService.delete(discDTO);
        then(discRepository).should().delete(disc());
    }


}