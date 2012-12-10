package com.example.spaceshipgame.model;

public class MissileMoveState {
	public static boolean	ENABLE	= true;
	public static boolean	DISABLE	= false;
	private boolean			isMoving;

	public MissileMoveState() {
		isMoving = MissileMoveState.DISABLE;
	}

	public void setIsMoving(boolean state) {
		isMoving = state;
	}

	public boolean getIsMoving() {
		return isMoving;
	}
}
