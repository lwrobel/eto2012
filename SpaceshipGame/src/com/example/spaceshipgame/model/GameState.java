package com.example.spaceshipgame.model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.*;

import com.example.spaceshipgame.controller.Controller;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * The Class GameState.
 * 
 * @author Aleksandra Spyra
 */
public class GameState implements IJSONSerializable {
	private Activity		activity;
	public List<Player>		players	= new CopyOnWriteArrayList<Player>();
	public CurrentPlayer	currentInstancePlayer;
	private ColourManager	colourManager;
	public Map				map;

	public GameState(Activity activity) {
		this.activity = activity;
		map = new Map();
		colourManager = new ColourManager();
		currentInstancePlayer = new CurrentPlayer(
				colourManager.getUniqueColourRGB(), map);
		players.add(currentInstancePlayer);

		/*
		 * for (int i = 0; i < 10; ++i) { Player player = new
		 * Player(colourManager.getUniqueColourRGB(), map); player.setID(i);
		 * players.add(player); }
		 */
	}

	public void deserialize(JSONObject obj) {
		try {
			deserializePlayers(obj.getJSONArray("players"));
		} catch (JSONException ex) {
			Log.e("Exception", ex.getMessage());
		}
	}

	public void showToastMessage(final String text) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				Toast toast = Toast.makeText(activity.getApplicationContext(),
						text, Toast.LENGTH_SHORT);
				toast.show();

			}
		});
	}

	public void deserializePlayers(JSONArray playersJSON) {
		HashMap<Integer, JSONObject> hashMap = new HashMap<Integer, JSONObject>();
		for (int i = 0; i < playersJSON.length(); ++i)
			try {
				JSONObject player = playersJSON.getJSONObject(i);
				int id = player.getInt("ID");
				if (id != currentInstancePlayer.getID())
					hashMap.put(id, player);
			} catch (Exception ex) {
				Log.e("Exception", ex.getMessage());
			}

		for (Player player : this.players)
			if (hashMap.containsKey(player.getID())) {
				player.deserialize(hashMap.get(player.getID()));
				hashMap.remove(player.getID());
			} else if (player.ID != currentInstancePlayer.getID()) {
				showToastMessage(String.format("Player #%d left game!",
						player.getID()));
				players.remove(player);
			}

		for (JSONObject jsonPlayer : hashMap.values()) {
			try {
				Log.d("new player", jsonPlayer.toString());
				Player player = new Player(new Colour(jsonPlayer.getJSONObject(
						"colour").getInt("value")), new Map());
				player.deserialize(jsonPlayer);
				showToastMessage(String.format("Player #%d join game!",
						player.getID()));
				players.add(player);
			} catch (Exception e) {
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
