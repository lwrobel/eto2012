package com.example.spaceshipgame.model;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Element implements IJSONSerializable{
	private Vector	position;
	private Vector	velocity;
	private double 	maxVelocityValue;
	private double 	acceleration;
	
	private int 	ID 	= -1;

	Random			r	= new Random();

	public Element(Map map) {
		position = new Vector(r.nextInt(map.size.x - map.MARGIN.x * 2)
				+ map.MARGIN.x, r.nextInt(map.size.y - map.MARGIN.y * 2)
				+ map.MARGIN.y);
		velocity = new Vector(r.nextInt(360), 10.0);
		
		acceleration = 0.1;
		maxVelocityValue = 15.0;
	}

	public Element(Vector position) {
		this.position = new Vector(position);
	}

	public Vector getPosition() {
		return position;
	}

	public Vector getVelocity() {
		return velocity;
	}
	
	public double getAcceleration() {
		return acceleration;
	}

	public double getMaxVelocity() {
		return maxVelocityValue;
	}
	
	public void setPosition(Vector vector) {
		position.set(vector);
	}
	
	public void setVelocity(Vector vector) {
	 	velocity.set(vector);
	}
	 	 
	public void setAcceleration(float acceleration) {
		this.acceleration = acceleration;
	}
	
	public void setMaxVelocity(double value) {
	 	maxVelocityValue = value;
	}
	
	public float getRotation() {
		return velocity.getAngle();
	}

	public void move(Vector vector) {
		position.add(vector);
	}

	public void moveAhead(int time) {
		// TODO use time in moving
		position.add(velocity);
	}

	public void increaseSpeed(int time) {
		// TODO use time in moving
		velocity.increaseValue(acceleration);
	}
	
	public void decreaseSpeed(int time) {
		// TODO use time in moving
		velocity.decreaseValue(acceleration);
	}
	
	public void moveBack(int time) {
		// TODO use time in moving
		position.sub(velocity);
	}

	public void rotateLeft(int time) {
		// TODO use time to count angle
		float angle = 3.0f;
		velocity.rotateLeft(angle);
	}

	public void rotateRight(int time) {
		// TODO use time to count angle
		float angle = 3.0f;
		velocity.rotateRight(angle);
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("ID", this.ID);
			obj.put("position", position.serialize());
			obj.put("velocity", velocity.serialize());
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
	
	public void deserialize(JSONObject obj) {
		try {
			position.deserialize(obj.getJSONObject("position"));
			velocity.deserialize(obj.getJSONObject("velocity"));
		} catch (JSONException ex) {
			Log.e("Exception", ex.getLocalizedMessage());
		}
	}
}
