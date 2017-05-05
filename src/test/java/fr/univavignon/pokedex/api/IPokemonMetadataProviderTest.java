package fr.univavignon.pokedex.api;

import static org.junit.Assert.*;
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
	
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	protected static PokemonMetadata pokemeta;
	
	@Mock
	private IPokemonMetadataProvider ipokemetaprovider;
	
	@Before
	public void setUp() {
		pokemeta = new PokemonMetadata(0,"Bulbizarre",126,126,90);
		configure(ipokemetaprovider);

	}
	
	public static void configure(IPokemonMetadataProvider i) {
		try {
			when(i.getPokemonMetadata(123)).thenReturn(new PokemonMetadata(0,"Bulbizarre",126,126,90));
		} catch (PokedexException e) {e.printStackTrace();}
	}
	
	
	@Test
	public void test() {
		
		PokemonMetadata pm = new PokemonMetadata(0,"Bulbizarre",126,126,90);
		int index = 0;
		assertNotNull(ipokemetaprovider);
		try {
			assertEquals(pm.getIndex(), ipokemetaprovider.getPokemonMetadata(index).getIndex());
			assertEquals(pm.getName(), ipokemetaprovider.getPokemonMetadata(index).getName());
			assertEquals(pm.getAttack(), ipokemetaprovider.getPokemonMetadata(index).getAttack());
			assertEquals(pm.getDefense(), ipokemetaprovider.getPokemonMetadata(index).getDefense());
			assertEquals(pm.getStamina(), ipokemetaprovider.getPokemonMetadata(index).getStamina());
		} catch (PokedexException e) {
			e.printStackTrace();
		}
		
		
	}
}
