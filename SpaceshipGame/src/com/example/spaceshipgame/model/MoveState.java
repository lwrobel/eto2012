package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class MoveState implements IJSONSerializable{
	
	public static enum State { ENABLE(0), DISABLE(1), ACCELERATE(2), SLOW(3);

		private int value;
		
		private State(int value) {
	        this.value = value;
	    }
		public int getValue() {
			
			return value;
		} 
	};
	
	//public static boolean	ENABLE	= true;
	//public static boolean	DISABLE	= false;
	
	private State			movingLeft;
	private State			movingRight;
	private State			movingUp;
	private State			movingDown;
	private State			attacking;

	public MoveState() {
		movingLeft = State.DISABLE;
		movingRight = State.DISABLE;
		movingUp = State.DISABLE;
		movingDown = State.DISABLE;
		attacking = State.DISABLE;
	}

	public void movingLeft(State state) {
		movingLeft = state;
	}

	public void movingRight(State state) {
		movingRight = state;
	}

	public void movingUp(State state) {
		movingUp = state;
	}

	public void movingDown(State state) {
		movingDown = state;
	}

	public void attacking(State state) {
		attacking = state;
	}
	
	public State movingLeft() {
		return movingLeft;
	}

	public State movingRight() {
		return movingRight;
	}

	public State movingUp() {
		return movingUp;
	}

	public State movingDown() {
		return movingDown;
	}
	
	public State attacking() {
		return attacking;
	}
	
	public static State valueOf(int value) {
		State[] valueEnums = State.values();
        for (State valueEnum : valueEnums) {
            if (valueEnum.getValue() == value)
            {
                return valueEnum;
            }
        }
        return State.DISABLE;
    }
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("movingLeft", movingLeft.getValue());
			obj.put("movingRight", movingRight.getValue());
			obj.put("movingUp", movingUp.getValue());
			obj.put("movingDown", movingDown.getValue());
			obj.put("attacking", attacking.getValue());
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			
			movingLeft = valueOf(obj.getInt("movingLeft"));
			movingRight = valueOf(obj.getInt("movingRight"));
			movingUp = valueOf(obj.getInt("movingUp"));
			movingDown = valueOf(obj.getInt("movingDown"));
			attacking = valueOf(obj.getInt("attacking"));
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
