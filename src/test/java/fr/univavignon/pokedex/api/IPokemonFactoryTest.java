package fr.univavignon.pokedex.api;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class IPokemonFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokemonFactory ipokeFacMock;

    @Before
    public void setUp() throws PokedexException {
        when(ipokeFacMock.createPokemon(1, 613, 64, 4000, 4)).thenReturn(new Pokemon(1, "bulbasaur", 126, 126, 90, 613, 64, 4000, 4, 56));
    }

    @Test
    public void testCreatePoke() {
        Pokemon pokemon = ipokeFacMock.createPokemon(1, 613, 64, 4000, 4);
        assertNotNull(pokemon);
        assertEquals("bulbasaur", pokemon.getName());
        assertEquals(126, pokemon.getAttack());
        assertEquals(126, pokemon.getDefense());
        assertEquals(90, pokemon.getStamina());
        assertEquals(613, pokemon.getCp());
        assertEquals(64, pokemon.getHp());
        assertEquals(4000, pokemon.getDust());
        assertEquals(4, pokemon.getCandy());
        assertEquals(56.0, pokemon.getIv());
    }
}
