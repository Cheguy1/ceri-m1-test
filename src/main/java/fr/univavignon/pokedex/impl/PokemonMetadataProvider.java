package fr.univavignon.pokedex.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jayway.restassured.path.json.JsonPath;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider, Serializable {

	
	private static final long serialVersionUID = 1L;

	public final String[] stats = {
			  "attack", 
			  "defense", 
			  "speed"
			};

	private HashMap<Integer, PokemonMetadata> pokemonMetadatas;

	public PokemonMetadataProvider() {
		pokemonMetadatas = new HashMap<>();
	}
	
	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		PokemonMetadata res = pokemonMetadatas.get(index);
		HttpURLConnection response = getHTTPResponse(index);
		String result =  getJSONContent(response);
		List<Object> metadatas = getMetadatasCollection(result);
		return convertPokemonMetadatas(index, metadatas);
	}

	public HttpURLConnection getHTTPResponse(int index) {
		URL url = null;
		HttpURLConnection request = null;
		System.setProperty("http.agent", "Gonna catch them all"); // Set user-sagent

		try {
			url = new URL("http://pokeapi.co/api/v2/pokemon/" + index);
		} catch (MalformedURLException e) {e.printStackTrace();}
		try {
			request = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {e.printStackTrace();}
		try {
			request.connect();
		} catch (IOException e) {e.printStackTrace();}

		return request;
	}

	
	public String getJSONContent(HttpURLConnection request) {
		StringBuilder sb = new StringBuilder();
		BufferedInputStream is = null;
		BufferedReader br;

		try {
			is = new BufferedInputStream(request.getInputStream());
			br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
		} catch (IOException e) {e.printStackTrace();}
		finally {
			if (is != null) {
				try { 
					is.close(); 
				} 
				catch (IOException e) {}
			}   
		}
		return sb.toString();
	}
	
	public List<Object> getMetadatasCollection(String jsonContent) {
		List<Object> metadatas = new ArrayList<Object>(); 
		metadatas.add(JsonPath.from(jsonContent).get("name"));
		for(String stat : stats) {
			metadatas.addAll(JsonPath.from(jsonContent).get("stats.findAll { stats -> stats.stat.name == '" + stat + "' }.base_stat "));
		}
		return metadatas;
	}

	public PokemonMetadata convertPokemonMetadatas(int index, List<Object> metadatas) {
		PokemonMetadata pokemonMetadata;
		String name = null;
		int attack = 0, defense = 0, stamina = 0;
		if(metadatas.size() == stats.length + 1) { 
			name = (String) metadatas.get(0);
			try {
				attack = (int)metadatas.get(1);
				defense = (int)metadatas.get(2);
				stamina = (int)metadatas.get(3);
			} catch (NumberFormatException e) {return null;}
		}
		return new PokemonMetadata(index, name, attack, defense, stamina);
	}
	

}
