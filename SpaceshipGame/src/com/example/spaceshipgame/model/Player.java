package com.example.spaceshipgame.model;

import java.util.ArrayList;


/**
 * The Class Player.
 *
 * Someone else task is to fill it in. 
 * I have just needed it for GameState.
 */
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
