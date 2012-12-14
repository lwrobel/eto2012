package com.example.spaceshipgame.model;

import android.graphics.Point;

public class Map {
	private static final int 	MARGIN_SIZE_X	= 100;
	private static final int 	MARGIN_SIZE_Y	= 50;
	private static final int 	MAP_SIZE_X		= 3000;
	private static final int 	MAP_SIZE_Y		= 1500;
	
	public final Point	MARGIN	= new Point(MARGIN_SIZE_X, MARGIN_SIZE_Y);
	public Point		size;

	public Map() {
		size = new Point(MAP_SIZE_X, MAP_SIZE_Y);
	}
}
