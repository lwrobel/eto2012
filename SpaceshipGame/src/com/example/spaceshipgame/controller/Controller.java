package com.example.spaceshipgame.controller;

import org.json.JSONObject;

import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;
import com.example.spaceshipgame.server.ServerSide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
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
		serverSide.start();	
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
				
				if (player.moveState.movingLeft() == MoveState.State.ENABLE)
					player.spaceship.rotateLeft(time);
				if (player.moveState.movingRight() == MoveState.State.ENABLE)
					player.spaceship.rotateRight(time);
				
				
				if (player.moveState.movingUp() == MoveState.State.ACCELERATE) {
					player.spaceship.increaseSpeed(time);
					
					if(player.spaceship.getVelocity() >= player.spaceship.getMaxVelocity()) {
						
						player.spaceship.setVelocity(player.spaceship.getMaxVelocity());
						player.moveState.movingUp(MoveState.State.ENABLE);
					}
					
					player.spaceship.moveAhead(time);
					
				} else if (player.moveState.movingUp() == MoveState.State.SLOW) {
					player.spaceship.decreaseSpeed(time);
					player.spaceship.moveAhead(time);
					
					if(player.spaceship.getVelocity() <= 2.0)
						player.moveState.movingUp(MoveState.State.DISABLE);
					
				} else if (player.moveState.movingUp() == MoveState.State.ENABLE) {
					player.spaceship.moveAhead(time);
				}
				
				
				
				if (player.moveState.movingDown() == MoveState.State.ACCELERATE) {
					player.spaceship.increaseSpeed(time);
					player.spaceship.moveBack(time);
					
					if(player.spaceship.getVelocity() >= player.spaceship.getMaxVelocity()) {
						
						player.spaceship.setVelocity(player.spaceship.getMaxVelocity());
						player.moveState.movingDown(MoveState.State.ENABLE);
					}
					
				} else if (player.moveState.movingDown() == MoveState.State.SLOW) {
					player.spaceship.decreaseSpeed(time);
					player.spaceship.moveBack(time);
					
					if(player.spaceship.getVelocity() <= 2.0)
						player.moveState.movingDown(MoveState.State.DISABLE);
					
				} else if (player.moveState.movingDown() == MoveState.State.ENABLE) {
					player.spaceship.moveBack(time);
				}
				
				player.getSpaceship()
						.validatePosition(new DoublePoint(0, 0), gameState.map.size,
								gameState.map.MARGIN);

				for (Element element : player.elements)

					if (element instanceof Missile) {
						element.moveAhead(time);
						if (!element.checkPosition(
								new DoublePoint(0, 0), gameState.map.size))
							player.elements.remove(element);
						// TODO delete from server
						// element.setPosition(new Vector(-100, -100));
					}
			}

		synchronized (gameState.currentInstancePlayer) {
			CurrentPlayer player = gameState.currentInstancePlayer;
			if (player.moveState.attacking() == MoveState.State.ENABLE) {
				Missile missile = new Missile(player, gameState.map);
				if (player.canAttack() && player.hasAmmunitionLeft()) {
					
					DoublePoint missilePosition = new DoublePoint(player.getSpaceship().getPosition());
					
					DoublePoint missileShift = new DoublePoint();
					missileShift.setByAngleAndLength(player.getSpaceship().getRotation(), player.getSpaceship().getHeigt()/2.0);
					missilePosition.addVector(missileShift);
					
					missile.setRotation(player.getSpaceship().getRotation());
					missile.setPosition(missilePosition);
					missile.setVelocity(15.0);
					player.attack(missile);
				}
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
			player.moveState.movingLeft(MoveState.State.DISABLE);
		}
	}

	public void onRightRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(MoveState.State.DISABLE);
		}
	}

	public void onUpRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		
		synchronized (player) {
			player.moveState.movingUp(MoveState.State.SLOW);
		}
	}

	public void onDownRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingDown(MoveState.State.SLOW);
		}
	}

	public void onAttackRelease() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.attacking(MoveState.State.DISABLE);
		}
	}

	public void onLeftPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingLeft(MoveState.State.ENABLE);
		}
	}

	public void onRightPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.movingRight(MoveState.State.ENABLE);
		}
	}

	public void onDownPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		
		synchronized (gameState.currentInstancePlayer) {
			
			if(player.moveState.movingUp() != MoveState.State.DISABLE) {
				player.moveState.movingUp(MoveState.State.DISABLE);
				player.getSpaceship().setVelocity(2.0);
			}

			player.moveState.movingDown(MoveState.State.ACCELERATE);
		}
		
	}

	public void onUpPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;

		synchronized (player) {
			
			if(player.moveState.movingDown() != MoveState.State.DISABLE) {
				player.moveState.movingDown(MoveState.State.DISABLE);
				player.getSpaceship().setVelocity(2.0);
			}
			
			player.moveState.movingUp(MoveState.State.ACCELERATE);
		}
		
	}

	public void onAttackPush() {
		CurrentPlayer player = gameState.currentInstancePlayer;
		synchronized (player) {
			player.moveState.attacking(MoveState.State.ENABLE);
		}
	}
}
