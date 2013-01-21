package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.DoublePoint;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missile;

public class MissileRenderer extends ElementRenderer {
	Paint	paint	= new Paint();
	Bitmap	missileBmp;
	Bitmap	rectBmp;

	public MissileRenderer(Context context) {
		super(context);
		missileBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.missile);
		rectBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.rectangle_missile);
	}

	@Override
	public void render(Canvas canvas, Element element, DoublePoint mapCenter,
			DoublePoint screenSize) {
		super.render(canvas, element, mapCenter, screenSize);
		Missile missile = (Missile) element;

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-missileBmp.getWidth() / 2,
				-missileBmp.getHeight() / 2);
		matrix.postRotate((float)missile.getRotation());
		matrix.postTranslate((float)(missile.getPosition().x - mapCenter.x
				+ screenSize.x / 2), (float)(missile.getPosition().y - mapCenter.y
				+ screenSize.y / 2));

		canvas.drawBitmap(missileBmp, matrix, null);
		renderColourRect(canvas, element, mapCenter, screenSize);
	}

	private void renderColourRect(Canvas canvas, Element element, DoublePoint mapCenter, DoublePoint screenSize) {
		Missile missile = (Missile) element;

		Paint paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColorFilter(new LightingColorFilter(0x00505050, missile
				.colour().toAndroidColor()));

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-rectBmp.getWidth() / 2, -rectBmp.getHeight() / 2);
		matrix.postRotate((float)missile.getRotation());
		matrix.postTranslate((float)(missile.getPosition().x - mapCenter.x
				+ screenSize.x / 2), (float)(missile.getPosition().y
				- mapCenter.y + screenSize.y / 2));
		canvas.drawBitmap(rectBmp, matrix, paint);
	}
}
