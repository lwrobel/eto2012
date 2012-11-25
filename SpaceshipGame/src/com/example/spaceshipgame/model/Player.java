package com.example.spaceshipgame.model;

import java.util.ArrayList;

public class Player {
	public ArrayList <Element> elements = new ArrayList <Element>();
	public Spaceship spaceship;
	
	public Player(){
		spaceship = new Spaceship();
       	elements.add(spaceship);
       	
       	elements.add(new Missile());
	}
	
	public Spaceship getSpaceship() {
		return spaceship;
	}
}
