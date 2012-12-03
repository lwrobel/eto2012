package com.example.spaceshipgame.model;

public class PlayerMoveState {
	public static boolean	ENABLE	= true;
	public static boolean	DISABLE	= false;
	private boolean			movingLeft;
	private boolean			movingRight;
	private boolean			movingUp;
	private boolean			movingDown;

	public PlayerMoveState() {
		movingLeft = PlayerMoveState.DISABLE;
		movingRight = PlayerMoveState.DISABLE;
		movingUp = PlayerMoveState.DISABLE;
		movingDown = PlayerMoveState.DISABLE;
	}

	public void movingLeft(boolean state) {
		movingLeft = state;
	}

	public void movingRight(boolean state) {
		movingRight = state;
	}

	public void movingUp(boolean state) {
		movingUp = state;
	}

	public void movingDown(boolean state) {
		movingDown = state;
	}

	public boolean movingLeft() {
		return movingLeft;
	}

	public boolean movingRight() {
		return movingRight;
	}

	public boolean movingUp() {
		return movingUp;
	}

	public boolean movingDown() {
		return movingDown;
	}
}