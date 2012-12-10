package com.example.spaceshipgame.renderer;

import android.content.Context;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;
import com.example.spaceshipgame.model.Spaceship;

public class ElementRendererFactory {
	private MissileRenderer		missileRenderer;
	private SpaceshipRenderer	spaceshipRenderer;
	private ElementRenderer		elementRenderer;

	public ElementRendererFactory(Context context) {
		missileRenderer = new MissileRenderer(context);
		spaceshipRenderer = new SpaceshipRenderer(context);
		elementRenderer = new ElementRenderer(context);
	}

	public ElementRenderer getElementRenderer(Element element) {
		if (element instanceof Missile)
			return missileRenderer;
		else if (element instanceof Spaceship)
			return spaceshipRenderer;
		else
			return elementRenderer;
	}
}
