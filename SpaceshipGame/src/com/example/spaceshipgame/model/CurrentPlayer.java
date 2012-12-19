package com.example.spaceshipgame.model;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class CurrentPlayer extends Player implements IJSONSerializable {
	private static final int	MS_BETWEEN_ATTACK	= 200;
	private int					lifeLevel			= 50;	// 0-100
	private AmmunitionLevel		ammunitionLevel;
	private Date				lastAttackTime;

	public CurrentPlayer(Colour colour, Map map) {
		super(colour, map);
		ammunitionLevel = new AmmunitionLevel();
		lastAttackTime = new Date();
	}

	public int getLifeLevel() {
		return lifeLevel;
	}

	public void setLifeLevel(int lifeLevel) {
		this.lifeLevel = lifeLevel;
	}

	public int getAmmunitionLevel() {
		return ammunitionLevel.getAmmunitionLevel();
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

	public boolean hasAmmunitionLeft() {
		return ammunitionLevel.hasAmmunitionLeft();
	}

	public void attack(Missile missile) {
		elements.add(missile);
		ammunitionLevel.attack();
		lastAttackTime = new Date();
	}

	public boolean canAttack() {
		return (new Date().getTime() - lastAttackTime.getTime() >= MS_BETWEEN_ATTACK);			
	}
}
