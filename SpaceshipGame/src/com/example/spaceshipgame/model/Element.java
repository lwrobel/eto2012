package com.example.spaceshipgame.model;

import java.util.Random;

public class Element {
	private int x, y; //it should be vector soon...
	Random r = new Random();
	
	public Element () {
		x=r.nextInt(300)+10;
		y=r.nextInt(300)+10;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
