package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private GameThread	thread;

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		thread = new GameThread(holder);
	}

	public void setController(Controller controller) {
		thread.setController(controller);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
	}

	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}
}