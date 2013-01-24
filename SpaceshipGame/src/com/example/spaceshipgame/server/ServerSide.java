package com.example.spaceshipgame.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.Date;

import org.json.JSONObject;

import android.util.Log;

import com.example.spaceshipgame.controller.Controller;
import com.example.spaceshipgame.model.GameState;

public class ServerSide extends Thread {
	private Controller			controller;
	private static int			port	= 6969;
	private static String		host	= "192.168.0.11";	// use your local
															// server ip
	private Socket				skt;
	private BufferedReader		in;
	private DataOutputStream	out;

	public ServerSide(Controller controller, GameState gameState) {
		this.controller = controller;
	}

	@Override
	public void run() {
		try {
			skt = new Socket(host, port);
			out = new DataOutputStream(skt.getOutputStream());
			in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
		} catch (Exception e) {
			Log.d("error", e.toString());
		}
		long lastTime = new Date().getTime();
		while (true) {
			long newTime = new Date().getTime();
			if (newTime - lastTime >= 20) {
				lastTime = newTime;
				JSONObject json = getGameState();
				try {
					//Log.d("aaaaaa", json.toString());
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
			else {
	            try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
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
}
