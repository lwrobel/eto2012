package com.example.spaceshipgame.model;

import org.json.JSONObject;

public class Missile extends Element {
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
}
