package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;

public class MissileRenderer extends ElementRenderer {
	Paint	paint	= new Paint();
	Bitmap	missileBmp;

	public MissileRenderer(Context context) {
		super(context);
		missileBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.missile);
	}

	@Override
	public void render(Canvas canvas, Element element, Point mapCenter,
			Point screenSize) {
		super.render(canvas, element, mapCenter, screenSize);
		Missile missile = (Missile) element;

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-missileBmp.getWidth() / 2,
				-missileBmp.getHeight() / 2);
		matrix.postRotate(missile.getRotation());
		matrix.postTranslate(missile.getPosition().getX() - mapCenter.x
				+ screenSize.x / 2, missile.getPosition().getY() - mapCenter.y
				+ screenSize.y / 2);

		canvas.drawBitmap(missileBmp, matrix, null);
	}
}
