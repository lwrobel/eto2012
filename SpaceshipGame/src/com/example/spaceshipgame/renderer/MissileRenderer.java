package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;

public class MissileRenderer extends ElementRenderer {
	Paint paint = new Paint();
	Bitmap missileBmp;
	
	public MissileRenderer(Context context) {
		super(context);
		missileBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);
	}
	
	@Override
	public void render(Canvas canvas, Element element, Point mapCenter, Point screenSize) {
		super.render(canvas, element, mapCenter, screenSize);
		Missile missile = (Missile) element;
        
        canvas.save();
        canvas.rotate(360-missile.getRotation(), (float)(missile.getPosition().getX() + (missileBmp.getScaledWidth(canvas) / 2)), (float)(missile.getPosition().getY() + (missileBmp.getScaledHeight(canvas) / 2)));
        canvas.drawBitmap(missileBmp, missile.getPosition().getX(), missile.getPosition().getY(), null);       
        canvas.restore();
        
	}
}
