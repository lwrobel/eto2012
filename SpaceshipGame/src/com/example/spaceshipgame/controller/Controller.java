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
	}

	public void init() {
		mainRenderer = new MainRenderer(getGameContext());
		gameState = new GameState();
		signalReceiver = new SignalReceiver(this);
		signalReceiver.init();
	}

	public Activity getGameActivity() {
		return gameActivity;
	}

	public Context getGameContext() {
		return gameActivity.getApplicationContext();
	}

	public void changeState() {
	}

	public void redraw(Canvas canvas) {
		mainRenderer.render(canvas, gameState);
	}

	private void showToastMessage(String text) {
		Toast toast = Toast.makeText(getGameContext(), text, Toast.LENGTH_SHORT);
		toast.show();
	}

	public void onLeftClicked() {
		gameState.currentInstancePlayer.getSpaceship().rotate(1);
		showToastMessage("left");
	}

	public void onRightClicked() {
		gameState.currentInstancePlayer.getSpaceship().rotate(-1);
		showToastMessage("right");
	}

	public void onUpClicked() {
		showToastMessage("up");
	}

	public void onDownClicked() {
		showToastMessage("down");
	}

	public void onAttackClicked() {
		showToastMessage("attack");
	}
}
