package com.example.spaceshipgame.model;

public class AmmunitionLevel {
	private final static int	MAX_AMMUNITION	= 100;
	private int					ammunition;

	public AmmunitionLevel() {
		ammunition = MAX_AMMUNITION;
	}

	public void attack() {
		ammunition = Math.max(0, ammunition - 1);
	}

	public int getAmmunitionLevel() {
		return (int) (((float) ammunition / (float) MAX_AMMUNITION) * 100);
	}
	
	public boolean hasAmmunitionLeft() {
		return ammunition>0;
	}
}
