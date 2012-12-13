package com.example.spaceshipgame.model;

public class CurrentPlayer extends Player {
	public PlayerMoveState moveState;
	
	public CurrentPlayer(Colour colour, Map map) {
		super(colour, map);
		moveState = new PlayerMoveState();
	}
}
