package com.example.spaceshipgame.model;

public class Spaceship extends Element {
	private int		width, height;
	private Player	player;

	public Spaceship(Player player, Map map) {
		super(map);
		this.player = player;
		width = r.nextInt(50) + 10;
		height = r.nextInt(50) + 10;
	}

	public Colour colour() {
		return player.colour;
	}

	public int getHeigt() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
