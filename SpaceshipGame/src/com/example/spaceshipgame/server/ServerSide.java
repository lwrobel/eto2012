package com.example.spaceshipgame.server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.spaceshipgame.controller.Controller;
import com.example.spaceshipgame.model.GameState;
import com.example.spaceshipgame.model.Spaceship;

public class ServerSide {
	private Controller	controller;
	private GameState	gameState;

	public ServerSide(Controller controller, GameState gameState) {
		this.controller = controller;
		this.gameState = gameState;
	}

	public void refreshState() {
		try {
			controller.deserializeState(buildFakeJson());
		} catch (JSONException e) {
		}
	}

	// remove it after add server
	private JSONObject buildFakeJson() throws JSONException {
		JSONObject data = new JSONObject();
		JSONArray players = new JSONArray();

		for (int i = 0; i <= 10; ++i) {
			JSONObject player = new JSONObject();
			JSONObject spaceship = new JSONObject();
			JSONObject missile = new JSONObject();
			JSONArray missiles = new JSONArray();

			if (i == 10) {
				Spaceship spaceship2 = new Spaceship(
						gameState.currentInstancePlayer, gameState.map);
				spaceship = spaceship2.serialize();
			}
			missiles.put(missile);

			player.put("ID", i);
			player.put("missiles", missiles);
			player.put("spaceship", spaceship);

			players.put(player);
		}
		data.put("players", players);
		return data;
	}
}
