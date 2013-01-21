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
import com.example.spaceshipgame.model.Spaceship;

public class SpaceshipRenderer extends ElementRenderer {
	Paint	paint	= new Paint();
	Bitmap	spaceShipBmp;
	Bitmap	rectBmp;

	public SpaceshipRenderer(Context context) {
		super(context);
		spaceShipBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.spaceship);
		rectBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.rectangle_spaceship);
	}

	@Override
	public void render(Canvas canvas, Element element, DoublePoint mapCenter, DoublePoint screenSize) {
		super.render(canvas, element, mapCenter, screenSize);
		Spaceship spaceship = (Spaceship) element;

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-spaceShipBmp.getWidth() / 2,
				-spaceShipBmp.getHeight() / 2);
		matrix.postRotate((float)spaceship.getRotation());
		matrix.postTranslate((float)(spaceship.getPosition().x - mapCenter.x + screenSize.x / 2),
				(float)(spaceship.getPosition().y - mapCenter.y + screenSize.y / 2));

		canvas.drawBitmap(spaceShipBmp, matrix, null);
		renderColourRect(canvas, element, mapCenter, screenSize);
	}

	private void renderColourRect(Canvas canvas, Element element, DoublePoint mapCenter, DoublePoint screenSize) {
		Spaceship spaceship = (Spaceship) element;

		Paint paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColorFilter(new LightingColorFilter(0x00505050, spaceship
				.colour().toAndroidColor()));

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-rectBmp.getWidth() / 2,
				-rectBmp.getHeight() / 2);
		matrix.postRotate((float)spaceship.getRotation());
		matrix.postTranslate((float)(spaceship.getPosition().x - mapCenter.x + screenSize.x / 2),
				(float)(spaceship.getPosition().y - mapCenter.y + screenSize.y / 2 ));
		canvas.drawBitmap(rectBmp, matrix, paint);
	}
}
