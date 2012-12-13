package com.example.spaceshipgame.model;

import android.graphics.Point;

public class Map {
	public final Point	MARGIN	= new Point(100, 50);
	public Point		size;

	public Map() {
		size = new Point(3000, 1500);
	}
}
