package fr.univavignon.pokedex.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerPersistable extends PokemonTrainer implements Serializable, Listener {

	private static final long serialVersionUID = 1L;
	public final String FILE_EXTENSION = ".ser";
	
	public PokemonTrainerPersistable(String name, Team team, IPokedex pokedex) {
		super(name, team, pokedex);
	}

	public void persist() {
		onChange();
	}
	
	
	@Override
	public void onChange() {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File(getName() + FILE_EXTENSION));
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
		} catch (FileNotFoundException e) {System.out.println("File error");}
		  catch (IOException e) {System.out.println("Error");}  
		finally {
			try {
				fos.close();
				oos.close();
			} catch (IOException e) {e.printStackTrace();}	
		}
	}
	
	

}