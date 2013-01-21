package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DoublePoint implements IJSONSerializable {
	public double x,y;
	
	public DoublePoint() {
		x = 0;
		y = 0;
	}
	
	public DoublePoint(double newX, double newY) {
		x = newX;
		y = newY;
	}
	
	public DoublePoint(DoublePoint point) {
		x = point.x;
		y = point.y;
	}
	
	public double getX() {
		return x;		
	}
	
	public double getY() {
		return y;		
	}
	
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public void set(double newX, double newY) {
		x = newX;
		y = newY;
	}
	
	public void setByAngleAndLength(double angle, double length) {
		double angleInRadius = angle * (Math.PI / 180.0);
		x = length * Math.cos(angleInRadius);
		y = length * Math.sin(angleInRadius);
	}
	
	
	public void addVector(DoublePoint vector) {
		x += vector.x;
		y += vector.y;
	}
	
	public void subVector(DoublePoint vector) {
		x -= vector.x;
		y -= vector.y;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("x", x);
			obj.put("y", y);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			x = obj.getDouble("x");
			y = obj.getDouble("y");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
