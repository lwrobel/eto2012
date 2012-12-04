package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

class GameThread extends Thread {
	private Controller		controller;
	private boolean			mRun;

	private long			mLastTime;

	private int				frameSamplesCollected	= 0;
	private int				frameSampleTime			= 0;
	private int				fps						= 0;

	private SurfaceHolder	mSurfaceHolder;

	private Paint			textPaint;

	public GameThread(SurfaceHolder surfaceHolder) {
		mSurfaceHolder = surfaceHolder;
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setTextSize(16);
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void run() {
		while (mRun) {
			Canvas c = null;
			try {
				c = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					updatePhysics();
					doDraw(c);
				}
			} catch (Exception ex) {
				if (c != null) {
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			} finally {
				if (c != null) {
					mSurfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}

	private void updatePhysics() {
		long now = System.currentTimeMillis();

		if (mLastTime != 0) {
			int time = (int) (now - mLastTime);
			controller.changeState(time);
			frameSampleTime += time;
			frameSamplesCollected++;
			if (frameSamplesCollected == 10) {
				fps = (int) (10000 / frameSampleTime);
				frameSampleTime = 0;
				frameSamplesCollected = 0;
			}
		}
		mLastTime = now;
	}

	private void doDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		canvas.drawText(fps + " fps", 10, 20, textPaint);
		controller.redraw(canvas);
		canvas.restore();
	}

	public void setRunning(boolean b) {
		mRun = b;
	}
}