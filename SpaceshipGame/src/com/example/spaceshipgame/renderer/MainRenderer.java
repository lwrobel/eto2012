package com.example.spaceshipgame.renderer;

import android.graphics.Canvas;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.*;

public class MainRenderer {
	
	public MainRenderer(){
	}
	
	public void render(Canvas canvas, GameState gameState){
		for(Player player : gameState.players){
			for(Element element: player.elements){
				ElementRenderer elementRenderer = ElementRendererFactory.getElementRender(element);
				elementRenderer.render(canvas, element);
			}
		}	
	}
}
