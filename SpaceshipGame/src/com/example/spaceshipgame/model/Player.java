package com.example.spaceshipgame.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player implements JSONSerializable{
	public ArrayList <Element> elements = new ArrayList <Element>();
	public Spaceship spaceship;
	public Colour colour;
	
	public Player(Colour colour, Map map){
		spaceship = new Spaceship(this, map);
       	elements.add(spaceship);
       	
       	elements.add(new Missile(map));
       	this.colour = colour;
	}
	
	public Spaceship getSpaceship() {
		return spaceship;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			JSONArray elem = new JSONArray();
		
			for(Element element: elements){
				elem.put(element.serialize());
			}
		
			obj.put("elements", elem);
			obj.put("spaceship", spaceship.serialize());
			obj.put("colour", colour.serialize());
			return obj;
		}
		catch(Exception ex){
			return null;
		}
	}
}
