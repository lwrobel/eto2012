package com.example.spaceshipgame.model;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Element implements IJSONSerializable{
	
	private DoublePoint position;
	private double rotation;
	private double velocity;
	private double 	maxVelocityValue;
	private double 	acceleration;
	private int 	ID 	= -1;

	Random			r	= new Random();

	public Element(Map map) {
		
		position = new DoublePoint(r.nextInt((int)(map.size.x - map.MARGIN.x * 2))
				+ map.MARGIN.x, r.nextInt((int)(map.size.y - map.MARGIN.y * 2))
				+ map.MARGIN.y);
		
		velocity = 0;
		rotation = r.nextInt(360);
		acceleration = 0.5;
		maxVelocityValue = 20.0;
		setID(new Random().nextInt(1000000));
	}

	public Element(DoublePoint newPosition) {
		position = new DoublePoint(newPosition);
	}

	public DoublePoint getPosition() {
		return position;
	}
	
	public double getVelocity() {
		return velocity;
	}
	
	public double getAcceleration() {
		return acceleration;
	}

	public double getMaxVelocity() {
		return maxVelocityValue;
	}
	
	public void setPosition(DoublePoint newPosition) {
		position = new DoublePoint(newPosition);
	}
	
	public void setVelocity(double newVelocity) {
	 	velocity = newVelocity;
	}
	 	 
	public void setRotation(double newRotation) {
		rotation = newRotation;
	}
	
	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setMaxVelocity(double value) {
	 	maxVelocityValue = value;
	}
	
	public double getRotation() {
		return rotation;
	}

	public void move(DoublePoint point) {
		position.addVector(point);
	}

	public void moveAhead(int time) {
		// TODO use time in moving
		double rotationInRadius = rotation * (Math.PI / 180.0);
		position.addVector(new DoublePoint(velocity * Math.cos(rotationInRadius), velocity * Math.sin(rotationInRadius)));
	}

	public void increaseSpeed(int time) {
		// TODO use time in moving
		velocity += acceleration;
	}
	
	public void decreaseSpeed(int time) {
		// TODO use time in moving
		velocity -= acceleration;
	}
	
	public void moveBack(int time) {
		// TODO use time in moving
		double rotationInRadius = rotation * (Math.PI / 180.0);
		position.subVector(new DoublePoint(velocity * Math.cos(rotationInRadius), velocity * Math.sin(rotationInRadius)));
	}

	public void rotateLeft(int time) {
		// TODO use time to count angle
		double step = 3;
		
		if( rotation - step < 0 )
			rotation = rotation - step + 360;
		else
			rotation -= step;
			
	}

	public void rotateRight(int time) {
		// TODO use time to count angle
		double step = 3;
		
		if( rotation + step > 360 )
			rotation = rotation + step - 360;
		else
			rotation += step;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public void validatePosition(DoublePoint min, DoublePoint max, DoublePoint margin) {

		double x = position.x;
		double y = position.y;
		
		if (x <= min.x + margin.x)
			x = min.x + margin.x;
		else if (x >= max.x - margin.x)
			x = max.x - margin.x;
		
		if (y <= min.y + margin.y)
			y = min.y + margin.y;
		else if (y >= max.y -  margin.y)
			y = max.y - margin.y;
		
		position.set(x,y);
	}
	
	public boolean checkPosition (DoublePoint min, DoublePoint max) {
		double x = position.x;
		double y = position.y;
		
		// TODO crash: check if is on other element + action?
		
		// if is out of board
		if (x < min.x - 40 || x > max.x + 40 || y < min.y - 40 || y > max.y + 40)
			return false;
		else
			return true;
	}
	
	public JSONObject serialize(){
		try{
			
			JSONObject obj = new JSONObject();
			obj.put("ID", this.ID);
			obj.put("position", this.position.serialize());
			obj.put("rotation", this.rotation);
			obj.put("velocity", this.velocity);
			obj.put("maxVelocityValue", this.maxVelocityValue);
			obj.put("acceleration", this.acceleration);
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			
			ID = obj.getInt("ID");
			position.deserialize(obj.getJSONObject("position"));
			rotation = obj.getDouble("rotation");
			velocity = obj.getDouble("velocity");
			maxVelocityValue = obj.getDouble("maxVelocityValue");
			acceleration = obj.getDouble("acceleration");
			
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
