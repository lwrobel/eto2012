package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;

public class MissileRenderer extends ElementRenderer {
	Paint paint = new Paint();
	
	@Override
	public void render(Canvas canvas, Element element, Context context) {
		super.render(canvas, element, context);
		Missile missile = (Missile) element;

        Bitmap kangoo = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);
        canvas.drawBitmap(kangoo, missile.getPosition().getX(), missile.getPosition().getY(), null);       
	}
}
