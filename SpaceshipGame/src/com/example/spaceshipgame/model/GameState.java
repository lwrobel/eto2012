package com.example.spaceshipgame.model;

import java.util.ArrayList;
import org.json.*;

/**
 * The Class GameState.
 * 
 * @author Aleksandra Spyra
 */
public class GameState implements JSONSerializable{
	public ArrayList<Player>	players	= new ArrayList<Player>();
	public CurrentPlayer		currentInstancePlayer;
	private ColourManager		colourManager;
	public Map					map;

	public GameState() {
		map = new Map();
		colourManager = new ColourManager();
		currentInstancePlayer = new CurrentPlayer(
				colourManager.getUniqueColourRGB(), map);
		players.add(currentInstancePlayer);

		for (int i = 0; i < 10; ++i)
			players.add(new Player(colourManager.getUniqueColourRGB(), map));
	}
	
	public JSONObject serialize(){
		try{
			JSONObject result = new JSONObject();
			result.put("currentInstancePlayer", currentInstancePlayer.serialize());
			return result;
		}
		catch(JSONException ex){
			return null;
		}
	}
	
}
