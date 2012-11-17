package com.example.spaceshipgame.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missle;

public class MissleRenderer extends ElementRenderer {
	Paint paint = new Paint();
	
	@Override
	public void render(Canvas canvas, Element element) {
		super.render(canvas, element);
		Missle missle = (Missle) element;
		paint.setAntiAlias(true);
		paint.setColor(Color.RED);
	    canvas.drawCircle(missle.getX(), missle.getY(), missle.getRadius(), paint);
	}
}
