package com.example.spaceshipgame.controller;


import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;

import android.content.Context;
import android.graphics.Canvas;

public class Controller {
	private MainRenderer mainRenderer;
	private SignalReceiver signalReceiver;
	private GameState gameState;
	
	public Controller(Context context) {
		mainRenderer = new MainRenderer(context);
		signalReceiver = new SignalReceiver();
		gameState = new GameState();
	}
	
	private void interpretSignal(Object ob) {
	}
	
	public void changeState() {
		interpretSignal(signalReceiver.getSignal());
	}
	
	public void redraw (Canvas canvas) {
		mainRenderer.render(canvas, gameState);
	}
}
