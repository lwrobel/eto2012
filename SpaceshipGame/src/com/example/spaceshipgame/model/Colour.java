package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Colour implements JSONSerializable {
	int	value;

	public Colour(int value) {
		this.value = value;
	}

	public int toAndroidColor() {
		return value;
	}

	public JSONObject serialize() {
		try {
			JSONObject obj = new JSONObject();
			obj.put("value", value);
			return obj;
		} catch (Exception ex) {
			return null;
		}
	}

	public void deserialize(JSONObject obj) {
		try {
			value = obj.getInt("value");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
