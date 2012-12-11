package com.example.spaceshipgame.controller;

import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.widget.Toast;

public class Controller {
	private MainRenderer	mainRenderer;
	private SignalReceiver	signalReceiver;
	private GameState		gameState;
	private Activity		gameActivity;

	public Controller(Activity activity) {
		gameActivity = activity;
		mainRenderer = new MainRenderer(getGameContext());
		gameState = new GameState();
		signalReceiver = new SignalReceiver(this);
	}

	public Activity getGameActivity() {
		return gameActivity;
	}

	public Context getGameContext() {
		return gameActivity.getApplicationContext();
	}

	public void changeState(int time) {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			if (player.moveState.movingLeft() == PlayerMoveState.ENABLE)
				player.spaceship.rotateLeft(time);
			if (player.moveState.movingRight() == PlayerMoveState.ENABLE)
				player.spaceship.rotateRight(time);
			if (player.moveState.movingUp() == PlayerMoveState.ENABLE)
				player.spaceship.moveAhead(time);
			if (player.moveState.movingDown() == PlayerMoveState.ENABLE)
				player.spaceship.moveBack(time);
			player.getSpaceship().getPosition().validate(new Point(0, 0), gameState.map.size, MainRenderer.MARGIN);
		}
	}

	public void redraw(Canvas canvas) {
		mainRenderer.render(canvas, gameState);
	}

	private void showToastMessage(String text) {
		Toast toast = Toast
				.makeText(getGameContext(), text, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onLeftRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingLeft(PlayerMoveState.DISABLE);
		}
	}

	public void onRightRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(PlayerMoveState.DISABLE);
		}
	}

	public void onUpRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingUp(PlayerMoveState.DISABLE);
		}
	}

	public void onDownRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingDown(PlayerMoveState.DISABLE);
		}
	}

	public void onAttackRelease() {
	}

	public void onLeftPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingLeft(PlayerMoveState.ENABLE);
		}
	}

	public void onRightPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(PlayerMoveState.ENABLE);
		}
	}

	public void onDownPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (gameState.currentInstancePlayer) {
			player.moveState.movingDown(PlayerMoveState.ENABLE);
		}
	}

	public void onUpPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingUp(PlayerMoveState.ENABLE);
		}
	}

	public void onAttackPush() {
	}
}
