package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class MoveState implements IJSONSerializable{
	public static boolean	ENABLE	= true;
	public static boolean	DISABLE	= false;
	private boolean			movingLeft;
	private boolean			movingRight;
	private boolean			movingUp;
	private boolean			movingDown;
	private boolean			attacking;

	public MoveState() {
		movingLeft = MoveState.DISABLE;
		movingRight = MoveState.DISABLE;
		movingUp = MoveState.DISABLE;
		movingDown = MoveState.DISABLE;
		attacking = MoveState.DISABLE;
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

	public void attacking(boolean state) {
		attacking = state;
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
	
	public boolean attacking() {
		return attacking;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("movingLeft", movingLeft);
			obj.put("movingRight", movingRight);
			obj.put("movingUp", movingUp);
			obj.put("movingDown", movingDown);
			obj.put("attacking", attacking);
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
			attacking = obj.getBoolean("attacking");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
