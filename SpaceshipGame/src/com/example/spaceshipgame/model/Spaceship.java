package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Spaceship extends Element implements IJSONSerializable{
	private int		width, height;
	private Player	player;

	public Spaceship(Player player, Map map) {
		super(map);
		this.player = player;
		width = r.nextInt(50) + 10;
		height = r.nextInt(50) + 10;
	}

	public Colour colour() {
		return player.colour;
	}

	public int getHeigt() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = super.serialize();
			obj.put("width", width);
			obj.put("height", height);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			super.deserialize(obj);
			width = obj.getInt("width");
			height = obj.getInt("height");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
