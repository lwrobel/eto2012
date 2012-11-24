package com.example.spaceshipgame.model;

public class Vector {

	private int x,y;
	
	public Vector(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(int angle, double value) {
		double angleInRadius = angle * (Math.PI / (double)180);
		
		x = (int) (value * Math.cos( angleInRadius ) );
		y = (int) (value * Math.sin( angleInRadius ) );
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
	
	public int getAngle() {
		return (int) (Math.atan(y/(double)x) * 180 / Math.PI);
	}	
	
	public void rotate(double angle) {
		// TODO
	}
	
	
}
