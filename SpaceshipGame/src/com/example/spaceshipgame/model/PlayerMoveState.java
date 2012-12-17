package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PlayerMoveState implements IJSONSerializable{
	public static boolean	ENABLE	= true;
	public static boolean	DISABLE	= false;
	private boolean			movingLeft;
	private boolean			movingRight;
	private boolean			movingUp;
	private boolean			movingDown;

	public PlayerMoveState() {
		movingLeft = PlayerMoveState.DISABLE;
		movingRight = PlayerMoveState.DISABLE;
		movingUp = PlayerMoveState.DISABLE;
		movingDown = PlayerMoveState.DISABLE;
	}

	public void movingLeft(boolean state) {
		movingLeft = state;
	}

	public void movingRight(boolean state) {
		movingRight = state;
	}

	public void movingUp(boolean state) {
		movingUp = state;
	}

	public void movingDown(boolean state) {
		movingDown = state;
	}

	public boolean movingLeft() {
		return movingLeft;
	}

	public boolean movingRight() {
		return movingRight;
	}

	public boolean movingUp() {
		return movingUp;
	}

	public boolean movingDown() {
		return movingDown;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("movingLeft", movingLeft);
			obj.put("movingRight", movingRight);
			obj.put("movingUp", movingUp);
			obj.put("movingDown", movingDown);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			movingLeft = obj.getBoolean("movingLeft");
			movingRight = obj.getBoolean("movingRight");
			movingUp = obj.getBoolean("movingUp");
			movingDown = obj.getBoolean("movingDown");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
