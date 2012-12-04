/**
 * @author Aleksandra Spyra
 */

package com.example.spaceshipgame.model;

import java.util.*;

import android.graphics.Color;

public class ColourManager {
	private Set<Integer> usedColours;
	private Random random;
	static final int MAX_COLOUR = 12;
	
	public ColourManager(){
		this.usedColours = new HashSet<Integer>();
		this.random = new Random();
	}
	
	public void resetAllColours(){
		this.usedColours.clear();
	}
	
	public void resetColour(Integer colour){
		this.usedColours.remove(colour);
	}
	
	/**
	 * Provides unique RGB colour or BLACK if all colours has been used
	 */
	public Colour getUniqueColourRGB(){
		int colour = this.getRandomRGBColour();
		while(this.usedColours.contains(new Integer(colour)) && !(this.usedColours.size() == MAX_COLOUR)){
			colour = this.getRandomRGBColour();
		}
		this.usedColours.add(new Integer(colour));
		return new Colour(colour);
	}
	
	private int getRandomRGBColour(){
		int rand = this.random.nextInt(MAX_COLOUR);
		switch(rand){
		case 0:
			return Color.BLACK;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.CYAN;
		case 3:
			return Color.DKGRAY;
		case 4:
			return Color.GRAY;
		case 5:
			return Color.GREEN;
		case 6:
			return Color.LTGRAY;
		case 7:
			return Color.MAGENTA;
		case 8:
			return Color.RED;
		case 9:
			return Color.TRANSPARENT;
		case 10:
			return Color.WHITE;
		case 11:
			return Color.YELLOW;
		default:
			return Color.BLACK;
		}	
	}
}