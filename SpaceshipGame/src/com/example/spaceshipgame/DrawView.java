package com.example.spaceshipgame;

import java.util.ArrayList;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.renderer.MainRenderer;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

//created for demo
public class DrawView extends View {
	MainRenderer mainRenderer = new MainRenderer();
	ArrayList <Element> elementsList;
	
	public DrawView(Context context) {
        super(context);            
    }

	public void setElementList (ArrayList <Element> elementsList_) {
		elementsList = new ArrayList <Element> (elementsList_);
	}
	
    @Override
    public void onDraw(Canvas canvas) {
    	mainRenderer.render(canvas, elementsList);
    }
}