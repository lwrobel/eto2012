package com.example.spaceshipgame.model;

import org.json.JSONObject;

public class Colour implements JSONSerializable{
	int value;
	
	public Colour(int value){
		this.value = value;
	}
	
	public int toAndroidColor() {
		return value;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("vaue", value);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
}
