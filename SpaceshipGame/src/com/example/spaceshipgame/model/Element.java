package com.example.spaceshipgame.model;

import java.util.Random;

import org.json.JSONObject;

public class Element {
	private Vector	position;
	private Vector	velocity;

	Random			r	= new Random();

	public Element(Map map) {
		position = new Vector(r.nextInt(map.size.x - map.MARGIN.x * 2)
				+ map.MARGIN.x, r.nextInt(map.size.y - map.MARGIN.y * 2)
				+ map.MARGIN.y);
		velocity = new Vector(r.nextInt(360), 10.0);
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
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			obj.put("position", position.serialize());
			obj.put("velocity", velocity.serialize());
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
}
