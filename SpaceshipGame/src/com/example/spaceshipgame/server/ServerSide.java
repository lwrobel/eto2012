package com.example.spaceshipgame.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.CharBuffer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;

import com.example.spaceshipgame.controller.Controller;
import com.example.spaceshipgame.model.GameState;
import com.example.spaceshipgame.model.Spaceship;

public class ServerSide extends AsyncTask<String, Void, JSONObject> {
	private Controller			controller;
	private GameState			gameState;
	private static int			port	= 6969;
	private static String		host	= "192.168.1.100";	// use your local
															// server ip
	private Socket				skt;
	private BufferedReader		in;
	private DataOutputStream	out;

	public ServerSide(Controller controller, GameState gameState) {
		this.controller = controller;
		this.gameState = gameState;
	}

	private JSONObject getGameState() {
		try {
			JSONObject json = new JSONObject();
			json.toString().length();
			json.put("type", "get");
			json.put("object", "gameState");
			out.writeBytes(json.toString());

			CharBuffer target = CharBuffer.allocate(4096);
			in.read(target);
			target.flip();

			JSONObject gameState = new JSONObject(target.toString());
			return gameState;
		} catch (Exception e) {
			Log.d("Exception", e.toString());
		}
		return null;
	}

	@Override
	protected JSONObject doInBackground(String... arg0) {
		try {
			skt = new Socket(host, port);
			out = new DataOutputStream(skt.getOutputStream());
			in = new BufferedReader(new InputStreamReader(skt.getInputStream()));

			if ((arg0[0] == "get") && (arg0[1] == "gameState"))
				return getGameState();
		} catch (Exception e) {
			Log.d("Exception", e.toString());
		}
		return null;
	}

	protected void onPostExecute(JSONObject json) {
		try {
			if (json == null)
				Log.d("game-json", "null");
			else if (!json.getString("status").equals("ok"))
				Log.d("game-json", json.getString("status"));
			else if (json.getString("object").equals("gameState"))
				controller.deserializeState(json.getJSONObject("data"));
			else
				Log.d("game-json", json.toString());
		} catch (Exception e) {
			Log.d("Exception", e.toString());
		}
	}

	public void refreshState() {
		execute("get","gameState");
	}
}
