package com.example.spaceshipgame.model;

public class CurrentMissile extends Missile {
	public MissileMoveState moveState;

	public CurrentMissile(Map map) {
		super (map);
		moveState = new MissileMoveState();
	}
}