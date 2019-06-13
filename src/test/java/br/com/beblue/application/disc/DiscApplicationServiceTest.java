package br.com.beblue.application.disc;

import br.com.beblue.application.disc.dto.DiscDTO;
import br.com.beblue.domain.disc.DiscRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static br.com.beblue.resources.TestsConstants.DISC_ID;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

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
    public void givenAnExistentIdWhenFindByDiscDTOThenDelegateToRepositoryAndReturn() {
        //given
        Long id = DISC_ID;
        //when
        discService.findById(id);
        //then

    }
}