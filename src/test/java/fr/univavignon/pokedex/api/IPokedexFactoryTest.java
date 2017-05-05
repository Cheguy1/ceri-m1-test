package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexFactoryTest {

	@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
	protected IPokedexFactory ipokedexFacMock;
    
    @Mock
    IPokedex ipokedexMock;

    @Mock
    IPokemonMetadataProvider ipokemetaprovider;

    @Mock
    IPokemonFactory ipokeFacMock;
    
    @Before
    public void setUp() {
        when(ipokedexFacMock.createPokedex(ipokemetaprovider, ipokeFacMock)).thenReturn(ipokedexMock);
    }
    
    @Test
    public void testCreatePokedex() {
        IPokedex pokedex = ipokedexFacMock.createPokedex(ipokemetaprovider, ipokeFacMock);
        assertNotNull(pokedex);

    }
}
	

