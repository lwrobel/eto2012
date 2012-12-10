package com.example.spaceshipgame.model;

public class CurrentMissile extends Missile {
	public MissileMoveState moveState;
	
	public CurrentMissile() {
		moveState = new MissileMoveState();
	}
}
