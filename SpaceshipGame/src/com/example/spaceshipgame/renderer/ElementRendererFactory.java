package com.example.spaceshipgame.renderer;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;
import com.example.spaceshipgame.model.Spaceship;

public final class ElementRendererFactory {
    private final static MissileRenderer missileRenderer = new MissileRenderer();
    private final static SpaceshipRenderer spaceshipRenderer = new SpaceshipRenderer();
    private final static ElementRenderer elementRenderer = new ElementRenderer();
    
    private ElementRendererFactory() {
    }
    
	public static ElementRenderer getElementRenderer (Element element) {
		if (element instanceof Missile)
			return missileRenderer;
		else if (element instanceof Spaceship)
			return spaceshipRenderer;
		else
			return elementRenderer;
	}
}
