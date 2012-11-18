package com.example.spaceshipgame.model;

public class Vector {

	private int x,y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(Vector vector) {
		set(vector);
	}
	
	public void add(Vector vector) {
		add(vector.x, vector.y);
	}
	
	public void add(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public void set(Vector vector) {
		set(vector.x, vector.y);
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
