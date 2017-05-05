package fr.univavignon.pokedex.api;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected IPokedex ipokedexMock;

    @Mock
    protected IPokedex ipokedexMockAdd;
    
    protected Pokemon Bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
    protected Pokemon Aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
    
    protected int pokeSize =  0;
    

    @Before
    public void setUp() throws PokedexException {
    	
        when(ipokedexMock.getPokemon(0)).thenReturn(Bulbizarre);

        List<Pokemon> pokeList = new ArrayList<>();
        pokeList.add(Bulbizarre);
        pokeList.add(Aquali);

        when(ipokedexMock.getPokemons()).thenReturn(Collections.unmodifiableList(pokeList));
        
        when(ipokedexMock.getPokemons(PokemonComparators.INDEX)).thenReturn(Collections.unmodifiableList(pokeList));
        when(ipokedexMock.getPokemons(PokemonComparators.NAME)).thenReturn(Collections.unmodifiableList(pokeList));
        when(ipokedexMock.getPokemons(PokemonComparators.CP)).thenReturn(Collections.unmodifiableList(pokeList));
        
        when(ipokedexMockAdd.addPokemon(Bulbizarre)).thenReturn(0);
        when(ipokedexMockAdd.addPokemon(Aquali)).thenReturn(1);
        when(ipokedexMockAdd.size()).thenReturn(2);
    	
        when(ipokedexMock.addPokemon(any())).then(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
              return pokeSize++;
            }
          });

        when(ipokedexMock.size()).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
              return pokeSize;
            }
          });     
    }
    
    
    @Test
    public void testAddPoke() {
    	
        assertEquals(0, ipokedexMockAdd.addPokemon(Bulbizarre));
        assertEquals(1, ipokedexMockAdd.addPokemon(Aquali));
        assertEquals(2, ipokedexMockAdd.size());
    }  

}
