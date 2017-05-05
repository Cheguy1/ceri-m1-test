package fr.univavignon.pokedex.impl;

import fr.univavignon.pokedex.api.IPokedexTest;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;

import static junit.framework.TestCase.assertEquals;


public class PokedexTest extends IPokedexTest {
	
	@Mock
	IPokemonMetadataProvider metadataProviderMock;
	@Mock
	IPokemonFactory pokeFactory;
	
    @Before
    public void setUp() {
    	ipokedexMock = new Pokedex(metadataProviderMock, pokeFactory);
    	ipokedexMockAdd = new Pokedex(metadataProviderMock, pokeFactory);
    }

}