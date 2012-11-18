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
	
	public Player(){
       	elements.add(new Spaceship());
       	elements.add(new Missile());
       	elements.add(new Missile());
	}
}
