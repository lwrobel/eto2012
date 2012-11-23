package com.example.spaceshipgame.model;

import com.example.spaceshipgame.R;
import com.example.spaceshipgame.controller.Controller;

import android.app.Activity;
import android.view.View;
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
	private Controller controller;

	public SignalReceiver(Controller controller) {
		this.controller = controller;
		setListeners();
	}

	public void init() {
		setListeners();
	}
	
	private void setListeners() {
		setLeftButtonListeners();
		setRightButtonListeners();
		setUpButtonListeners();
		setDownButtonListeners();
		setAttackButtonListeners();
	}

	private void setLeftButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.leftButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onLeftClicked();
			}
		});
	}

	private void setRightButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.rightButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onRightClicked();
			}
		});
	}

	private void setUpButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity.findViewById(R.id.upButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onUpClicked();
			}
		});
	}

	private void setDownButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.downButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onDownClicked();
			}
		});
	}

	private void setAttackButtonListeners() {
		Activity activity = controller.getGameActivity();
		ImageButton button = (ImageButton) activity
				.findViewById(R.id.attackButton);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				onAttackClicked();
			}
		});
	}

	private void onAttackClicked() {
		controller.onAttackClicked();
	}

	private void onLeftClicked() {
		controller.onLeftClicked();
	}

	private void onRightClicked() {
		controller.onRightClicked();
	}

	private void onUpClicked() {
		controller.onUpClicked();
	}

	private void onDownClicked() {
		controller.onDownClicked();
	}
}
