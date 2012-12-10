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
	Bitmap missleBmp;
	
	public MissileRenderer(Context context) {
		super(context);
		missleBmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.missile);
	}
	
	@Override
	public void render(Canvas canvas, Element element) {
		super.render(canvas, element);
		Missile missile = (Missile) element;
        
        canvas.save();
        canvas.rotate(360-missile.getRotation(), (float)(missile.getPosition().getX() + (missleBmp.getScaledWidth(canvas) / 2)), (float)(missile.getPosition().getY() + (missleBmp.getScaledHeight(canvas) / 2)));
        canvas.drawBitmap(missleBmp, missile.getPosition().getX(), missile.getPosition().getY(), null);       
        canvas.restore();
        
	}
}
