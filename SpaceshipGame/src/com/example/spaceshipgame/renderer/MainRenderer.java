package com.example.spaceshipgame.renderer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.model.*;

@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
public class MainRenderer {
	private DoublePoint			screenSize;
	private DoublePoint			mapCenter;
	private Bitmap				background;

	ElementRendererFactory	elementRendererFactory;
	Context					context;
	Activity				activity;

	public MainRenderer(Context context, Activity activity, GameState gameState) {
		this.context = context;
		this.activity = activity;
		elementRendererFactory = new ElementRendererFactory(context);
		screenSize = getScreenSize();
		mapCenter = getMapCenter(gameState);
		createBackground();
	}

	private void updateProgressBar(GameState gameState) {
		CurrentPlayer player= gameState.currentInstancePlayer;
		ProgressBar lifeProgressBar = (ProgressBar) activity.findViewById(R.id.lifeProgressBar);
		lifeProgressBar.setProgress(player.getLifeLevel());

		ProgressBar ammunitionProgressBar = (ProgressBar) activity.findViewById(R.id.ammunitionProgressBar);
		ammunitionProgressBar.setProgress(player.getAmmunitionLevel());
	}

	private DoublePoint getScreenSize() {
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		return new DoublePoint(dm.widthPixels, dm.heightPixels);
	}

	private DoublePoint getMapCenter(GameState gameState) {
		int x, y;
		Spaceship s = gameState.currentInstancePlayer.getSpaceship();
		Map m = gameState.map;
		x = Math.max((int) s.getPosition().x, (int) screenSize.x / 2);
		y = Math.max((int) s.getPosition().y, (int) screenSize.y / 2);
		x = Math.min(x, (int) (m.size.x - screenSize.x / 2));
		y = Math.min(y, (int) (m.size.y - screenSize.y / 2));
		return new DoublePoint(x, y);
	}

	public void render(Canvas canvas, GameState gameState) {
		updateMapCenter(gameState);
		renderMap(canvas, gameState);
		for (Player player : gameState.players) {
			ElementRenderer elementRenderer = elementRendererFactory
					.getElementRenderer(player.spaceship);
			elementRenderer.render(canvas, player.spaceship, mapCenter, screenSize);
			for (Element element : player.elements) {
				elementRenderer = elementRendererFactory.getElementRenderer(element);
				elementRenderer.render(canvas, element, mapCenter, screenSize);
			}
		}
		updateProgressBar(gameState);
	}

	private void createBackground() {
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.background);

		Bitmap bmOverlay = Bitmap.createBitmap((int)screenSize.x * 2,
				(int)screenSize.y * 2, Bitmap.Config.ARGB_8888);
		Rect src = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
		Canvas canvas = new Canvas(bmOverlay);
		canvas.drawBitmap(bmp, src, new Rect((int)screenSize.x * 0,
				(int)screenSize.y * 0, (int)screenSize.x * 1, (int)screenSize.y * 1), null);
		canvas.drawBitmap(bmp, src, new Rect((int)screenSize.x * 0,
				(int)screenSize.y * 1, (int)screenSize.x * 1, (int)screenSize.y * 2), null);
		canvas.drawBitmap(bmp, src, new Rect((int)screenSize.x * 1,
				(int)screenSize.y * 0, (int)screenSize.x * 2, (int)screenSize.y * 1), null);
		canvas.drawBitmap(bmp, src, new Rect((int)screenSize.x * 1,
				(int)screenSize.y * 1, (int)screenSize.x * 2, (int)screenSize.y * 2), null);
		background = bmOverlay;
	}

	private void updateMapCenter(GameState gameState) {
		Spaceship currentPlayerSpaceship = gameState.currentInstancePlayer.spaceship;
		int x = (int) currentPlayerSpaceship.getPosition().x;
		int y = (int) currentPlayerSpaceship.getPosition().y;

		DoublePoint MARGIN = gameState.map.MARGIN;
		mapCenter.setX( updateMapCoord((int)mapCenter.x, (int)screenSize.x, (int)MARGIN.x, x) );
		mapCenter.setY( updateMapCoord((int)mapCenter.y, (int)screenSize.y, (int)MARGIN.y, y) );
	}

	private void renderMap(Canvas canvas, GameState gameState) {
		DoublePoint mapSize = gameState.map.size;
		int left, top;

		left = calculateRenderMapCoord((int)mapSize.x, (int)mapCenter.x, (int)screenSize.x);
		top = calculateRenderMapCoord((int)mapSize.y, (int)mapCenter.y, (int)screenSize.y);

		left %= background.getWidth() / 2;
		top %= background.getHeight() / 2;

		Rect src = new Rect(left, top, left + background.getWidth() / 2, top
				+ background.getHeight() / 2);
		Rect dst = new Rect(0, 0, (int)screenSize.x, (int)screenSize.y);
		canvas.drawBitmap(background, src, dst, null);
	}

	private int updateMapCoord(int mapCoord, int screenCoord, int marginCoord,
			int playerCoord) {
		if (mapCoord + screenCoord / 2 - marginCoord < playerCoord)
			return (playerCoord - screenCoord / 2 + marginCoord);
		if (mapCoord - screenCoord / 2 + marginCoord > playerCoord)
			return (playerCoord + screenCoord / 2 - marginCoord);
		return mapCoord;
	}

	private int calculateRenderMapCoord(int mapCoord, int mapCenterCoord,
			int screenCoord) {
		if (mapCenterCoord - screenCoord / 2 < 0)
			return 0;
		if (mapCenterCoord + screenCoord / 2 > mapCoord)
			return (mapCoord - screenCoord);
		return (mapCenterCoord - screenCoord / 2);
	}
}
