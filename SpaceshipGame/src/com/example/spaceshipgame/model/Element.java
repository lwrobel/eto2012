package com.example.spaceshipgame.model;

import java.util.Random;

public class Element {
	private Vector position;
	private Vector velocity;
	
	Random r = new Random();
	
	public Element () {
		position = new Vector(r.nextInt(300) + 10, r.nextInt(300) + 10);
		velocity = new Vector(5, 100.0);
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
	
	public void rotate(double angle) {

	}
}

