package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class DrawView extends View {
	private Controller controller;
	
	public DrawView(Context context) {
        super(context);            
    }
	
	public void setController(Controller controller_) {
		controller=controller_;	
	}
	
    @Override
    public void onDraw(Canvas canvas) {
    	controller.redraw(canvas);
    }
}