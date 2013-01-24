package com.example.spaceshipgame.model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.*;

import android.util.Log;

/**
 * The Class GameState.
 * 
 * @author Aleksandra Spyra
 */
public class GameState implements IJSONSerializable {
	public List<Player>		players	= new CopyOnWriteArrayList<Player>();
	public CurrentPlayer	currentInstancePlayer;
	private ColourManager	colourManager;
	public Map				map;

	public GameState() {
		map = new Map();
		colourManager = new ColourManager();
		currentInstancePlayer = new CurrentPlayer(
				colourManager.getUniqueColourRGB(), map);
		currentInstancePlayer.setID(10);
		players.add(currentInstancePlayer);

		/*for (int i = 0; i < 10; ++i) {
			Player player = new Player(colourManager.getUniqueColourRGB(), map);
			player.setID(i);
			players.add(player);
		}*/
	}

	public void deserialize(JSONObject obj) {
		try {
			deserializePlayers(obj.getJSONArray("players"));
		} catch (JSONException ex) {
			Log.e("Exception", ex.getMessage());
		}
	}

	public void deserializePlayers(JSONArray playersJSON) {
		HashMap<Integer, JSONObject> hashMap = new HashMap<Integer, JSONObject>();
		for (int i = 0; i < playersJSON.length(); ++i)
			try {
				JSONObject player = playersJSON.getJSONObject(i);
				int id = player.getInt("ID");
				hashMap.put(id, player);
			} catch (Exception ex) {
				Log.e("Exception", ex.getMessage());
			}

		for (Player player : this.players)
			if (hashMap.containsKey(player.getID())) {
				player.deserialize(hashMap.get(player.getID()));
				hashMap.remove(player.getID());
			}
		for (JSONObject jsonPlayer : hashMap.values()) {
			try {
				Log.d("new player",  jsonPlayer.toString());
				Player player = new Player(new Colour(jsonPlayer.getJSONObject("colour").getInt("value")), new Map());
				player.deserialize(jsonPlayer);
				players.add(player);
			}
			catch (Exception e) {
			}
		}
	}

	public JSONObject serialize() {
		try {
			JSONObject result = new JSONObject();
			result.put("currentInstancePlayer",
					currentInstancePlayer.serialize());
			return result;
		} catch (JSONException ex) {
			return null;
		}
	}
}
