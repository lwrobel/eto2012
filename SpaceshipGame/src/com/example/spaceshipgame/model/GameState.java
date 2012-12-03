package com.example.spaceshipgame.model;

import java.util.ArrayList;

/**
 * The Class GameState.
 * @author Aleksandra Spyra
 */
public class GameState {
	public ArrayList <Player> players = new ArrayList <Player> ();
	public CurrentPlayer currentInstancePlayer;
	
	public GameState(){
		currentInstancePlayer = new CurrentPlayer();
		players.add(currentInstancePlayer);
		
		for (int i=0; i<2; ++i)
			players.add(new Player());
	}
}
