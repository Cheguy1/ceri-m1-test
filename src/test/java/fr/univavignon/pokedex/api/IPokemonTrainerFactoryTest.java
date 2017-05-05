package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class IPokemonTrainerFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokemonTrainerFactory ipokeTrainFacMock;

    @Mock
    protected IPokedexFactory ipokedexFacMock;

    @Mock
    protected IPokedex ipokedexMock;
    
    @Before
    public void setUp() throws PokedexException {
        when(ipokeTrainFacMock.createTrainer("Sacha", Team.VALOR, ipokedexFacMock)).thenReturn(new PokemonTrainer("Sacha", Team.VALOR, ipokedexMock));    
        when(ipokedexMock.size()).thenReturn(1);
    }

    @Test
    public void testTrainer() {
        PokemonTrainer s = ipokeTrainFacMock.createTrainer("Sacha", Team.VALOR, ipokedexFacMock);
        assertEquals("Sacha", s.getName());
        assertEquals(Team.VALOR, s.getTeam());
        assertNotNull(s);
        assertNotNull(s.getPokedex());        
    }
}
