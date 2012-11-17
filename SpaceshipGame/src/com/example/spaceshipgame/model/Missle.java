package com.example.spaceshipgame.model;

public class Missle extends Element {
	private int radius;
	
	public Missle () {
		radius = r.nextInt(20)+10;
	}
	
	public int getRadius() {
		return radius;
	}
}
