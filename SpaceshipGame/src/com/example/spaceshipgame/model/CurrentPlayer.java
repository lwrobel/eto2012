package com.example.spaceshipgame.model;

public class CurrentPlayer extends Player {
	public PlayerMoveState moveState;
	
	public CurrentPlayer() {
		super();
		moveState = new PlayerMoveState();
	}
}
