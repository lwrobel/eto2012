package com.example.spaceshipgame.model;

import java.util.ArrayList;

/**
 * The Class GameState.
 * @author Aleksandra Spyra
 */
public class GameState {
	public ArrayList <Player> players = new ArrayList <Player> ();
	
	public GameState(){
		for (int i=0; i<5; ++i)
			players.add(new Player());
	}
}
