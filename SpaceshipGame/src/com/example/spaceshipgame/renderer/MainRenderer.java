package com.example.spaceshipgame.renderer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.*;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class MainRenderer {
	private Point				screenSize;
	private Point				mapCenter;
	private Bitmap				background;

	ElementRendererFactory		elementRendererFactory;
	Context						context;

	public MainRenderer(Context context, GameState gameState) {
		this.context = context;
		elementRendererFactory = new ElementRendererFactory(context);
		screenSize = getScreenSize();
		mapCenter = getMapCenter(gameState);
		createBackground();
	}

	private Point getScreenSize() {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return new Point(dm.widthPixels, dm.heightPixels);
	}

	private Point getMapCenter(GameState gameState) {
		int x, y;
		Spaceship s = gameState.currentInstancePlayer.getSpaceship();
		Map m = gameState.map;
		x = Math.max((int) s.getPosition().getX(), screenSize.x / 2);
		y = Math.max((int) s.getPosition().getY(), screenSize.y / 2);
		x = Math.min(x, m.size.x - screenSize.x / 2);
		y = Math.min(y, m.size.y - screenSize.y / 2);
		return new Point(x, y);
	}

	public void render(Canvas canvas, GameState gameState) {
		updateMapCenter(gameState);
		renderMap(canvas, gameState);
		for (Player player : gameState.players) {
			for (Element element : player.elements) {
				ElementRenderer elementRenderer = elementRendererFactory
						.getElementRenderer(element);
				elementRenderer.render(canvas, element, mapCenter, screenSize);
			}
		}
	}

	private void createBackground() {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.background);

		Bitmap bmOverlay = Bitmap.createBitmap(screenSize.x * 2,
				screenSize.y * 2, Bitmap.Config.ARGB_8888);
		Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp, src, new Rect(screenSize.x * 0,
				screenSize.y * 0, screenSize.x * 1, screenSize.y * 1), null);
		canvas.drawBitmap(bmp, src, new Rect(screenSize.x * 0,
				screenSize.y * 1, screenSize.x * 1, screenSize.y * 2), null);
		canvas.drawBitmap(bmp, src, new Rect(screenSize.x * 1,
				screenSize.y * 0, screenSize.x * 2, screenSize.y * 1), null);
		canvas.drawBitmap(bmp, src, new Rect(screenSize.x * 1,
				screenSize.y * 1, screenSize.x * 2, screenSize.y * 2), null);
		background = bmOverlay;
	}

	private void updateMapCenter(GameState gameState) {
		Spaceship currentPlayerSpaceship = gameState.currentInstancePlayer.spaceship;
		int x = (int) currentPlayerSpaceship.getPosition().getX();
		int y = (int) currentPlayerSpaceship.getPosition().getY();

		Point MARGIN = gameState.map.MARGIN;
		if (mapCenter.x + screenSize.x / 2 - MARGIN.x < x) {
			mapCenter.x = x - screenSize.x / 2 + MARGIN.x;
		} else if (mapCenter.x - screenSize.x / 2 + MARGIN.x > x) {
			mapCenter.x = x + screenSize.x / 2 - MARGIN.x;
		}

		if (mapCenter.y + screenSize.y / 2 - MARGIN.y < y) {
			mapCenter.y = y - screenSize.y / 2 + MARGIN.y;
		} else if (mapCenter.y - screenSize.y / 2 + MARGIN.y > y) {
			mapCenter.y = y + screenSize.y / 2 - MARGIN.y;
		}
	}

	private void renderMap(Canvas canvas, GameState gameState) {
		Point mapSize = gameState.map.size;
		int left, top;

		if (mapCenter.x - screenSize.x / 2 < 0)
			left = 0;
		else if (mapCenter.x + screenSize.x / 2 > mapSize.x)
			left = mapSize.x - screenSize.x;
		else
			left = mapCenter.x - screenSize.x / 2;

		if (mapCenter.y - screenSize.y / 2 < 0)
			top = 0;
		else if (mapCenter.y + screenSize.y / 2 > mapSize.y)
			top = mapSize.y - screenSize.y;
		else
			top = mapCenter.y - screenSize.y / 2;

		left %= background.getWidth() / 2;
		top %= background.getHeight() / 2;

		Rect src = new Rect(left, top, left + background.getWidth() / 2, top
				+ background.getHeight() / 2);
		Rect dst = new Rect(0, 0, screenSize.x, screenSize.y);
		canvas.drawBitmap(background, src, dst, null);
	}
}
