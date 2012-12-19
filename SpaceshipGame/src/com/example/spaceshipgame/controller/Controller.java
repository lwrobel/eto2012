package com.example.spaceshipgame.controller;

import org.json.JSONObject;

import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;
import com.example.spaceshipgame.server.ServerSide;

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
	private ServerSide		serverSide;

	public Controller(Activity activity) {
		gameActivity = activity;
		gameState = new GameState();
		mainRenderer = new MainRenderer(getGameContext(), getGameActivity(),
				gameState);
		signalReceiver = new SignalReceiver(this);
		serverSide = new ServerSide(this, gameState);
		serverSide.refreshState();
	}

	public Activity getGameActivity() {
		return gameActivity;
	}

	public Context getGameContext() {
		return gameActivity.getApplicationContext();
	}

	public void deserializeState(JSONObject data) {
		gameState.deserialize(data);
	}

	public void changeState(int time) {
		for (Player player : gameState.players)
			synchronized (player) {
				if (player.moveState.movingLeft() == MoveState.ENABLE)
					player.spaceship.rotateLeft(time);
				if (player.moveState.movingRight() == MoveState.ENABLE)
					player.spaceship.rotateRight(time);
				if (player.moveState.movingUp() == MoveState.ENABLE)
					player.spaceship.moveAhead(time);
				if (player.moveState.movingDown() == MoveState.ENABLE)
					player.spaceship.moveBack(time);
				player.getSpaceship()
						.getPosition()
						.validate(new Point(0, 0), gameState.map.size,
								gameState.map.MARGIN);

				for (Element element : player.elements)

					if (element instanceof Missile) {
						element.moveAhead(time);
						if (!element.getPosition().checkPosition(
								new Point(0, 0), gameState.map.size))
							player.elements.remove(element);
						// TODO delete from server
						// element.setPosition(new Vector(-100, -100));
					}
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
			player.moveState.movingLeft(MoveState.DISABLE);
		}
	}

	public void onRightRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(MoveState.DISABLE);
		}
	}

	public void onUpRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingUp(MoveState.DISABLE);
		}
	}

	public void onDownRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingDown(MoveState.DISABLE);
		}
	}

	public void onAttackRelease() {
	}

	public void onLeftPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingLeft(MoveState.ENABLE);
		}
	}

	public void onRightPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(MoveState.ENABLE);
		}
	}

	public void onDownPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (gameState.currentInstancePlayer) {
			player.moveState.movingDown(MoveState.ENABLE);
		}
	}

	public void onUpPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingUp(MoveState.ENABLE);
		}
	}

	public void onAttackPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		Missile missile = new Missile(player, gameState.map);

		synchronized (player) {
			if (player.hasAmmunitionLeft()) {
				missile.setPosition(player.getSpaceship().getPosition());
				missile.setVelocity(player.getSpaceship().getVelocity());
				player.attack(missile);
			}
		}
	}
}
