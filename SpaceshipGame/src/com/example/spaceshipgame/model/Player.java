package com.example.spaceshipgame.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player implements JSONSerializable{
	public ArrayList <Element> elements = new ArrayList <Element>();
	public Spaceship spaceship;
	public Colour colour;
	protected int ID = -1;
	
	public Player(Colour colour, Map map){
		spaceship = new Spaceship(this, map);
       	elements.add(spaceship);
       	
       	elements.add(new Missile(map));
       	this.colour = colour;
	}
	
	public Spaceship getSpaceship() {
		return spaceship;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public JSONObject serialize(){
		try{
			JSONObject obj = new JSONObject();
			JSONArray elem = new JSONArray();
		
			for(Element element: elements){
				elem.put(element.serialize());
			}
		
			obj.put("ID", this.ID);
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
