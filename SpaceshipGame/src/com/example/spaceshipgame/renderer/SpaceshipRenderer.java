package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Spaceship;

public class SpaceshipRenderer extends ElementRenderer {
	Paint paint = new Paint();
	
	@Override
	public void render(Canvas canvas, Element element, Context context) {
		super.render(canvas, element, context);
		Spaceship spaceship = (Spaceship) element;
        Bitmap kangoo = BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);
        canvas.drawBitmap(kangoo, spaceship.getPosition().getX(), spaceship.getPosition().getY(), null);       
	}
}
