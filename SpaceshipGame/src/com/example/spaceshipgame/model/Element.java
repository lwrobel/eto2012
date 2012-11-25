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
	
	public void moveAhead() {
		position.add(velocity);
	}
	
	public void moveBack() {
		position.sub(velocity);
	}
	
	public void rotateLeft(double angle) {
		velocity.rotateLeft((float)angle);
	}
	
	public void rotateRight(double angle) {
		velocity.rotateRight((float)angle);
	}
}

