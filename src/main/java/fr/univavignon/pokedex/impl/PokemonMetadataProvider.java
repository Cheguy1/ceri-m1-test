package fr.univavignon.pokedex.impl;


import java.io.IOException;
import java.io.Serializable;

import org.jsoup.Jsoup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		
		try {
			String json = Jsoup.connect("http://pokeapi.co/api/v2/pokemon/" + index + "/").timeout(100000).userAgent("Mozilla").ignoreContentType(true).execute().body();
			JsonElement je = new JsonParser().parse(json);
			JsonObject  jo = je.getAsJsonObject();
			String name = jo.get("name").toString();
			
			
			JsonArray ja = jo.getAsJsonArray("stats");
			JsonObject def = ja.get(3).getAsJsonObject();
			String defense = def.get("base_stat").toString();
					
			JsonObject att = ja.get(4).getAsJsonObject();
			String attaque = att.get("base_stat").toString();
			
			PokemonMetadata pm = new PokemonMetadata(index,name.replace("\"", ""), Integer.parseInt(attaque), Integer.parseInt(defense), 0);
			return pm;
		} 
		catch (IOException e) {System.out.println(e);}
		return null;
	}
}