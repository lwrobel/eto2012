package com.example.spaceshipgame.model;

import org.json.JSONObject;

public class Spaceship extends Element implements JSONSerializable{
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
}
