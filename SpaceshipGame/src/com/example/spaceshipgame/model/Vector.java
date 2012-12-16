package com.example.spaceshipgame.model;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Point;
import android.util.Log;

public class Vector implements JSONSerializable{

	private float x,y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(float angle, double value) {
		double angleInRadius = angle * (Math.PI / (double)180);
		
		x = (float) (value * Math.cos( angleInRadius ));
		y = (float) (value * Math.sin( angleInRadius ) );
	}
	
	public Vector(float angle) {
		this(angle,(float)10.0);
	}
	
	public Vector(Vector vector) {
		set(vector);
	}
	
	public void add(Vector vector) {
		add(vector.x, vector.y);
	}
	
	public void sub(Vector vector) {
		sub(vector.x, vector.y);
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public void sub(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void set(Vector vector) {
		set(vector.x, vector.y);
	}
	
	public void set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getAngle() {
		
		if(x>0 && y>=0)
			return (float) (Math.atan(y/(double)x) * 180 / Math.PI);
		
		if(x<0 && y>=0)
			return (float) (180 - Math.atan(-y/(double)x) * 180 / Math.PI);
		
		if(x<0 && y<=0)
			return (float) (180 + Math.atan(y/(double)x) * 180 / Math.PI);
		
		if(x>0 && y<=0)
			return (float) (360 - Math.atan(-y/(double)x) * 180 / Math.PI);
		
		if(y==0)
			return 0;
		
		if(y>0)
			return 90;
		
		return 270;
	}	
	
	public double getValue() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void rotateLeft(float angle) {
		rotateRight(-angle);
	}
	
	public void rotateRight(float angle) {
		float previousAngle = getAngle();
		Vector newVector = new Vector(angle+previousAngle, getValue());
		set(newVector);
	}
	
	public void validate (Point min, Point max, Point margin) {
		x = getX();
		y = getY();
		
		if (x <= min.x + margin.x)
			x=min.x + margin.x;
		else if (x >= max.x - margin.x)
			x = max.x - margin.x;
		
		if (y <= min.y + margin.y)
			y=min.y + margin.y;
		else if (y >= max.y -  margin.y)
			y = max.y - margin.y;
		set(x,y);
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
			x = obj.getInt("x");
			y = obj.getInt("y");
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
