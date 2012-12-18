package com.example.spaceshipgame.model;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.controller.Controller;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;

/**
 * The Class SignalReceiver.
 * 
 * This class will be used to receive signals such as screen touch. It will
 * exchange information with Controller.
 * 
 * @author Aleksandra Spyra
 */

public class SignalReceiver {
	private Controller	controller;

	public SignalReceiver(Controller controller) {
		this.controller = controller;
		setListeners();
	}

	private void setListeners() {
		setLeftButtonListeners();
		setRightButtonListeners();
		setUpButtonListeners();
		setDownButtonListeners();
		setAttackButtonListeners();
		setAttackViewListeners();
	}

	private void setLeftButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.leftButton);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onLeftPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onLeftRelease();
				}
				return true;
			}
		});
	}

	private void setRightButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.rightButton);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onRightPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onRightRelease();
				}
				return true;
			}
		});
	}

	private void setUpButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity.findViewById(R.id.upButton);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onUpPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onUpRelease();
				}
				return true;
			}
		});
	}

	private void setDownButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.downButton);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onDownPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onDownRelease();
				}
				return true;
			}
		});
	}

	private void setAttackButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.attackButton);
		button.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onAttackPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onAttackRelease();
				}
				return true;
			}
		});
	}

	private void setAttackViewListeners() {
		Activity activity = controller.getGameActivity();
		View gameView = (View) activity
				.findViewById(R.id.gameView);
		gameView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent event) {
				if (event.getAction() == android.view.MotionEvent.ACTION_DOWN)
					onAttackPush();
				else if (event.getAction() == android.view.MotionEvent.ACTION_UP) {
					onAttackRelease();
				}
				return true;
			}
		});
	}

	private void onLeftRelease() {
		controller.onLeftRelease();
	}

	private void onLeftPush() {
		controller.onLeftPush();
	}

	private void onRightRelease() {
		controller.onRightRelease();
	}

	private void onRightPush() {
		controller.onRightPush();
	}

	private void onDownRelease() {
		controller.onDownRelease();
	}

	private void onDownPush() {
		controller.onDownPush();
	}

	private void onUpRelease() {
		controller.onUpRelease();
	}

	private void onUpPush() {
		controller.onUpPush();
	}

	private void onAttackRelease() {
		controller.onAttackRelease();
	}

	private void onAttackPush() {
		controller.onAttackPush();
	}
}
