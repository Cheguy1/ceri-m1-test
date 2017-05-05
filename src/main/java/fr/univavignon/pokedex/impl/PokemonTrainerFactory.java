package fr.univavignon.pokedex.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory ipokedexFac) {
		PokemonTrainer t = loadTrainer(name);
		if(t == null) { 
			PokemonFactory pokeFac = new PokemonFactory();
			PokemonMetadataProvider pokemetaprovider = new PokemonMetadataProvider();
			Pokedex pokedex = (Pokedex) ipokedexFac.createPokedex(pokemetaprovider, pokeFac);
			t = new PokemonTrainerPersistable(name, team, pokedex);
			pokedex.addObserver((Listener) t);
		} 
		return t;
	}
	
	public PokemonTrainerPersistable loadTrainer(String tName) {
		FileInputStream  fis = null;
		ObjectInputStream ois = null;
		PokemonTrainerPersistable res = null;
		
		try {
			File file = new File(tName  +".ser");
			if(file.exists() && !file.isDirectory()) { 
				fis = new FileInputStream (file);
				ois = new ObjectInputStream(fis);
				res = (PokemonTrainerPersistable) ois.readObject();
			} else {
				return null;
			}
			
		} catch (ClassNotFoundException e) {System.err.println("Error class");}
		  catch (IOException e) {System.err.println("Error");} 
		finally {
			try {
					fis.close();
					ois.close();
			} catch (IOException e) {System.err.println("Error");}	
		}
		return res;
	}

}
