package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IPokemonMetadataProviderTest {
	
	static PokemonMetadata PokemonMeta;
	
	@Mock
	IPokemonMetadataProvider ipokemetaprovider;
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	
	@Before
	public void setUp() {
		PokemonMeta = new PokemonMetadata(0, "Bulbizarre", 126, 126, 90);
		configure(ipokemetaprovider);

	}
	
    @Test
    public void testGetPokeMetadata() throws PokedexException {
        PokemonMetadata p = ipokemetaprovider.getPokemonMetadata(25);
        
        assertTrue(p != null);
		assertEquals(p.getName(), "Bulbizarre");
		assertEquals(p.getAttack(), 126);
		assertEquals(p.getDefense(), 126);
		assertEquals(p.getStamina(), 90);
        
    }
	
	public static void configure(IPokemonMetadataProvider i) {
		try {
			when(i.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
		} catch (PokedexException e) {e.printStackTrace();}
	}

}
