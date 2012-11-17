package com.example.spaceshipgame.renderer;

import java.util.ArrayList;

import android.graphics.Canvas;

import com.example.spaceshipgame.model.Element;

public class MainRenderer {
	
	//remove it after add render(GameState)
	public void render(Canvas canvas, ArrayList <Element> elementsList) {
    	for (Element element: elementsList) {
    		ElementRenderer elementRenderer = ElementRendererFactory.getElementRender(element);
    		elementRenderer.render(canvas, element);
    	}	
	}
}
