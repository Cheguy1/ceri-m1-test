package fr.univavignon.pokedex.impl;

import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonFactory implements IPokemonFactory, Serializable {
	
	public static final long serialVersionUID = 1L;

	private PokemonMetadataProvider pokemetaprovider;
	
	
	public PokemonFactory() {
		pokemetaprovider = new PokemonMetadataProvider();
	}
	
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadata pokemonMD;
		Pokemon poke;
		try {
			pokemonMD = pokemetaprovider.getPokemonMetadata(index);
		} catch (PokedexException e) {return null;}
		return new Pokemon(index, pokemonMD.getName(), pokemonMD.getAttack(), pokemonMD.getDefense(), pokemonMD.getStamina(), cp, hp, dust, candy, 54);
		
	}


}