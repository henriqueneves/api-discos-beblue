package br.com.beblue.application.disc;

import br.com.beblue.domain.disc.Disc;
import br.com.beblue.domain.genre.Genre;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscApplicationServiceTest {

    @Mock
    private DiscService discService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void findById() {
            }

}