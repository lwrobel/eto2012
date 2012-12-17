package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Missile extends Element implements IJSONSerializable{
	private int radius;
	
	public Missile (Map map) {
		super (map);
		radius = r.nextInt(20) + 10;
	}
	
	public int getRadius() {
		return radius;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = super.serialize();
			obj.put("radius", radius);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			super.deserialize(obj);
			radius = obj.getInt("radius");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
