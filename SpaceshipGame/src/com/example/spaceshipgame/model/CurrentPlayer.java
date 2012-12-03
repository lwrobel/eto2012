package com.example.spaceshipgame.model;

public class CurrentPlayer extends Player {
	public PlayerMoveState moveState;
	
	public CurrentPlayer(Colour colour) {
		super(colour);
		moveState = new PlayerMoveState();
	}
}
