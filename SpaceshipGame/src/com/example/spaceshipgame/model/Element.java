package com.example.spaceshipgame.model;

import java.util.Random;

public class Element {
	private Vector position;
	private Vector velocity;
	
	Random r = new Random();
	
	public Element () {
		position = new Vector(r.nextInt(300) + 10, r.nextInt(300) + 10);
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
	
	public void setPosition(Vector vector) {
		position.set(vector);
	}
	
	public void setVelocity(Vector vector) {
		velocity.set(vector);
	}
	
	public void moveAhead(int time) {
		//TODO use time in moving  
		position.add(velocity);
	}
	
	public void moveBack(int time) {
		//TODO use time in moving
		position.sub(velocity);
	}
	
	public void rotateLeft(int time) {
		//TODO use time to count angle
		float angle = 3.0f;
		velocity.rotateLeft(angle);
	}
	
	public void rotateRight(int time) {
		//TODO use time to count angle
		float angle = 3.0f;
		velocity.rotateRight(angle);
	}
}

