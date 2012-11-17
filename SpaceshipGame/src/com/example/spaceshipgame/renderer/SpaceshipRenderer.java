package com.example.spaceshipgame.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Spaceship;

public class SpaceshipRenderer extends ElementRenderer {
	Paint paint = new Paint();
	
	@Override
	public void render(Canvas canvas, Element element) {
		super.render(canvas, element);
		Spaceship spaceship = (Spaceship) element;
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
	    Rect rectangle = new Rect(spaceship.getX(), spaceship.getY(), spaceship.getX()+spaceship.getWidth(), spaceship.getY()+spaceship.getHeigt());
	    canvas.drawRect(rectangle, paint);
	}
}
