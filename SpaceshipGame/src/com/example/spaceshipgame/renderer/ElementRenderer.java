package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Canvas;

import com.example.spaceshipgame.model.DoublePoint;
import com.example.spaceshipgame.model.Element;

public class ElementRenderer {
	protected Context	context;

	public ElementRenderer(Context context) {
		this.context = context;
	}

	public void render(Canvas canvas, Element element, DoublePoint mapCenter, DoublePoint screenSize) {
	}
}
