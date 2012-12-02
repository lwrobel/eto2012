package com.example.spaceshipgame.model;

import java.util.ArrayList;

public class Player {
	public ArrayList <Element> elements = new ArrayList <Element>();
	public Spaceship spaceship;
	public Colour colour;
	
	public Player(Colour colour){
		spaceship = new Spaceship();
       	elements.add(spaceship);
       	
       	elements.add(new Missile());
       	this.colour = colour;
	}
	
	public Spaceship getSpaceship() {
		return spaceship;
	}
}
