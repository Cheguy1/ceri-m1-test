package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import org.junit.Before;
import org.mockito.Mock;


public class PokedexTest extends IPokedexTest {
	
	@Mock
	IPokemonMetadataProvider ipokemetaprovider;
	@Mock
	IPokemonFactory ipokeFacMock;
	
    @Override
	@Before
    public void setUp() {
    	ipokedexMock = new Pokedex(ipokemetaprovider, ipokeFacMock);
    	ipokedexMockAdd = new Pokedex(ipokemetaprovider, ipokeFacMock);
    }

}