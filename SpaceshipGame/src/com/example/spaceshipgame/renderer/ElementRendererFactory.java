package com.example.spaceshipgame.renderer;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missle;
import com.example.spaceshipgame.model.Spaceship;

public final class ElementRendererFactory {
    private final static MissleRenderer missleRenderer = new MissleRenderer();
    private final static SpaceshipRenderer spaceshipRenderer = new SpaceshipRenderer();
    private final static ElementRenderer elementRenderer = new ElementRenderer();
    
    private ElementRendererFactory() {
    }
    
	public static ElementRenderer getElementRender (Element element) {
		if (element instanceof Missle)
			return missleRenderer;
		else if (element instanceof Spaceship)
			return spaceshipRenderer;
		else
			return elementRenderer;
	}
}
