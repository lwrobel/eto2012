package com.example.spaceshipgame.model;

import org.json.JSONObject;

public class CurrentPlayer extends Player {
	public PlayerMoveState moveState;
	
	public CurrentPlayer(Colour colour, Map map) {
		super(colour, map);
		moveState = new PlayerMoveState();
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = super.serialize();
			obj.put("moveState", moveState.serialize());
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
}
