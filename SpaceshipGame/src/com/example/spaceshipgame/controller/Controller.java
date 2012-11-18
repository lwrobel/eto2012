package com.example.spaceshipgame.controller;

import com.example.spaceshipgame.model.*;
import com.example.spaceshipgame.renderer.*;

import android.graphics.Canvas;

public class Controller {
	private MainRenderer mainRenderer;
	private SignalReceiver signalReceiver;
	private GameState gameState;
	private Canvas canvas;
	
	public Controller(){
	}
	
	private void interpretSignal(Object ob){
	}
	
	public void changeState(){
		interpretSignal(signalReceiver.getSignal());
		mainRenderer.render(canvas, gameState);
	}
}
