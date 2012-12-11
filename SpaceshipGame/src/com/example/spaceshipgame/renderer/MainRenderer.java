package com.example.spaceshipgame.renderer;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.*;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class MainRenderer {
	private static final int	X_MARGIN	= 100;
	private static final int	Y_MARGIN	= 50;
	private Point				screenSize;
	private Point				mapCenter;
	private Bitmap				background;

	ElementRendererFactory		elementRendererFactory;
	Context						context;

	public MainRenderer(Context context_) {
		context = context_;
		elementRendererFactory = new ElementRendererFactory(context_);

		background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background);
		mapCenter = new Point(200, 200); //TODO
		screenSize = new Point();
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getSize(screenSize);
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

	private void updateMapCenter(GameState gameState) {
		Spaceship currentPlayerSpaceship = gameState.currentInstancePlayer.spaceship;
		int x = (int) currentPlayerSpaceship.getPosition().getX();
		int y = (int) currentPlayerSpaceship.getPosition().getY();

		if (mapCenter.x + screenSize.x / 2 - X_MARGIN < x) {
			mapCenter.x = x - screenSize.x / 2 + X_MARGIN;
		} else if (mapCenter.x - screenSize.x / 2 + X_MARGIN > x) {
			mapCenter.x = x + screenSize.x / 2 - X_MARGIN;
		}

		if (mapCenter.y + screenSize.y / 2 - Y_MARGIN < y) {
			mapCenter.y = y - screenSize.y / 2 + Y_MARGIN;
		} else if (mapCenter.y - screenSize.y / 2 + Y_MARGIN > y) {
			mapCenter.y = y + screenSize.y / 2 - Y_MARGIN;
		}
	}

	private void renderMap(Canvas canvas, GameState gameState) {
		Point mapSize = gameState.map.size;
		int left, right, top, bottom;

		if (mapCenter.x - screenSize.x / 2 < 0) {
			left = 0;
			right = screenSize.x;
		} else if (mapCenter.x + screenSize.x / 2 > mapSize.x) {
			left = mapSize.x - screenSize.x;
			right = mapSize.x;
		} else {
			left = mapCenter.x - screenSize.x / 2;
			right = mapCenter.x + screenSize.x / 2;
		}

		if (mapCenter.y - screenSize.y / 2 < 0) {
			top = 0;
			bottom = screenSize.y;
		} else if (mapCenter.y + screenSize.y / 2 > mapSize.y) {
			top = mapSize.y - screenSize.y;
			bottom = mapSize.y;
		} else {
			top = mapCenter.y - screenSize.y / 2;
			bottom = mapCenter.y + screenSize.y / 2;
		}

		Rect src = new Rect(left, top, right, bottom);
		Rect dst = new Rect(0, 0, screenSize.x, screenSize.y);
		canvas.drawBitmap(background, src, dst, null);
	}
}
