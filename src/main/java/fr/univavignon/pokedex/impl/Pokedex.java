package fr.univavignon.pokedex.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex, Serializable  {

	private static final long serialVersionUID = 1L;
	
	
	protected IPokemonFactory pokeFactory;
	private ArrayList<Pokemon> ALPokemon;
	private IPokemonMetadataProvider pokeMDProvider;
	private List<Listener> observers;
	

	public Pokedex(IPokemonMetadataProvider pokeMDProvider, IPokemonFactory pokeFactory) {
		this.pokeMDProvider = pokeMDProvider;
		this.pokeFactory = pokeFactory;
		ALPokemon = new ArrayList<Pokemon>();
		observers = new ArrayList<Listener>();
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return ALPokemon.get(index);
	}

	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		if (index < 0){
			try {
				throw new PokedexException("Index invalide");
			} catch (PokedexException e) {e.printStackTrace();}
		}else{
		return pokeFactory.createPokemon(index, cp, hp, dust, candy);
		}
		return null;
	}

	@Override
	public Pokemon getPokemon(int id){
		if(id >= ALPokemon.size()) {
			try {
				throw new PokedexException("ID invalide");
			} catch (PokedexException e) {e.printStackTrace();}
		}
		return ALPokemon.get(id);
	}

	@Override
	public List<Pokemon> getPokemons() {
		return Collections.unmodifiableList(ALPokemon);
	}


	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> listPok = new ArrayList<Pokemon>(ALPokemon);
		Collections.sort(listPok, order);
		return Collections.unmodifiableList(listPok);
	}
	
	@Override
	public int size() {
		return ALPokemon.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		ALPokemon.add(pokemon);
		return ALPokemon.size()-1;
	}
	
	public void addObserver(Listener e) {
		observers.add(e);
	}
	
	public void notifyObservers() {
		for(Listener e : observers) {
			if(e != null) {
				e.onChange();
			}
		}
	}

	
}
