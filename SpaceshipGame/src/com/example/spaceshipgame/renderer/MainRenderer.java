package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Canvas;

import com.example.spaceshipgame.model.*;

public class MainRenderer {
	private ElementRendererFactory elementRendererFactory;
	
	public MainRenderer(Context context) {
		elementRendererFactory = new ElementRendererFactory(context);
	}
	
	public void render(Canvas canvas, GameState gameState) {
		for(Player player : gameState.players){
			for(Element element: player.elements){
				ElementRenderer elementRenderer = elementRendererFactory.getElementRenderer(element);
				elementRenderer.render(canvas, element);
			}
		}	
	}
}
