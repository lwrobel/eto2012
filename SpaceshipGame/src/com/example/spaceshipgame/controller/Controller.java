package com.example.spaceshipgame.controller;

import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.widget.Toast;

public class Controller {
	private MainRenderer mainRenderer;
	private SignalReceiver signalReceiver;
	private GameState gameState;
	private Activity gameActivity;

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
	}

	public void redraw(Canvas canvas) {
		mainRenderer.render(canvas, gameState);
	}

	private void showToastMessage(String text) {
		Toast toast = Toast.makeText(getGameContext(), text, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onLeftRelease() {
		gameState.currentInstancePlayer.getSpaceship().rotateLeft(10);
	}

	public void onRightRelease() {
		gameState.currentInstancePlayer.getSpaceship().rotateRight(10);
	}

	public void onUpRelease() {
		gameState.currentInstancePlayer.getSpaceship().moveAhead();
	}

	public void onDownRelease() {
		gameState.currentInstancePlayer.getSpaceship().moveBack();
	}

	public void onAttackRelease() {
	}

	public void onLeftPush() {
	}

	public void onRightPush() {
	}

	public void onDownPush() {
	}

	public void onUpPush() {
	}

	public void onAttackPush() {
	}
}
