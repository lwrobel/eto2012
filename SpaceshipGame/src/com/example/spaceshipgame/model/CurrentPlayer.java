package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CurrentPlayer extends Player implements JSONSerializable{
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
	
	public void deserialize(JSONObject obj) {
		try {
			super.deserialize(obj);
			moveState.deserialize(obj.getJSONObject("moveState"));
		} catch (JSONException ex) {
			Log.e("Exception", ex.getMessage());
		}
	}
}
