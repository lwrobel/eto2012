package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CurrentPlayer extends Player implements IJSONSerializable {
	public PlayerMoveState	moveState;
	private int				lifeLevel		= 50;	// 0-100
	private int				ammunitionLevel	= 60;	// 0-100

	public CurrentPlayer(Colour colour, Map map) {
		super(colour, map);
		moveState = new PlayerMoveState();
	}

	public int getLifeLevel() {
		return lifeLevel;
	}

	public void setLifeLevel(int lifeLevel) {
		this.lifeLevel = lifeLevel;
	}

	public int getAmmunitionLevel() {
		return ammunitionLevel;
	}

	public void setAmmunitionLevel(int ammunitionLevel) {
		this.ammunitionLevel = ammunitionLevel;
	}

	public JSONObject serialize() {
		try {
			JSONObject obj = super.serialize();
			obj.put("moveState", moveState.serialize());
			return obj;
		} catch (Exception ex) {
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
