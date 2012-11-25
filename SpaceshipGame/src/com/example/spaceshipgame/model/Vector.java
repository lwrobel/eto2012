package com.example.spaceshipgame.model;

public class Vector {

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
	
	public Vector(Vector vector) {
		set(vector);
	}
	
	public void add(Vector vector) {
		add(vector.x, vector.y);
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
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
	
	public void rotate(double angle) {
		// TODO
	}
	
	
}
