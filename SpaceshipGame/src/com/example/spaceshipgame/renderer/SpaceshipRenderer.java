package com.example.spaceshipgame.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Spaceship;

public class SpaceshipRenderer extends ElementRenderer {
	Paint	paint	= new Paint();
	Bitmap	spaceShipBmp;
	Bitmap	bannerBmp;

	public SpaceshipRenderer(Context context) {
		super(context);
		spaceShipBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.spaceship);
		bannerBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.banner);
	}

	@Override
	public void render(Canvas canvas, Element element, Point mapCenter, Point screenSize) {
		super.render(canvas, element, mapCenter, screenSize);
		Spaceship spaceship = (Spaceship) element;

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-spaceShipBmp.getWidth() / 2,
				-spaceShipBmp.getHeight() / 2);
		matrix.postRotate(spaceship.getRotation());
		matrix.postTranslate(spaceship.getPosition().getX() - mapCenter.x + screenSize.x / 2,
				spaceship.getPosition().getY() - mapCenter.y + screenSize.y / 2);

		canvas.drawBitmap(spaceShipBmp, matrix, null);
		renderBanner(canvas, element, mapCenter, screenSize);
	}

	private void renderBanner(Canvas canvas, Element element, Point mapCenter, Point screenSize) {
		Spaceship spaceship = (Spaceship) element;

		Paint paint = new Paint();
		paint.setDither(true);
		paint.setAntiAlias(true);
		paint.setFilterBitmap(true);
		paint.setColorFilter(new LightingColorFilter(0x00505050, spaceship
				.colour().toAndroidColor()));

		Matrix matrix = new Matrix();
		matrix.reset();
		matrix.postTranslate(-bannerBmp.getWidth() / 2, -bannerBmp.getHeight() / 2);
		matrix.postRotate(spaceship.getRotation());
		matrix.postTranslate(spaceship.getPosition().getX() - mapCenter.x + screenSize.x / 2,
				spaceship.getPosition().getY() - mapCenter.y + screenSize.y / 2);
		canvas.drawBitmap(bannerBmp, matrix, paint);
	}
}
