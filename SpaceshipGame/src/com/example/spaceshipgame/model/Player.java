package com.example.spaceshipgame.model;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Player implements IJSONSerializable{
	public MoveState	moveState;
	public List <Element> elements = new CopyOnWriteArrayList <Element>();
	public Spaceship spaceship;
	public Colour colour;
	protected int ID = -1;
	
	public Player(Colour colour, Map map){
		moveState = new MoveState();
		spaceship = new Spaceship(this, map);
		this.colour = colour;
		
       	elements.add(spaceship);
       	elements.add(new Missile(this, map));
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
	
	public void deserialize(JSONObject obj) {
		try {
			spaceship.deserialize(obj.getJSONObject("spaceship"));
			deserializeElements(obj.getJSONArray("missiles"));
			colour.deserialize(obj.getJSONObject("colour"));
		} catch (JSONException e) {
		}
	}
	
	private void deserializeElements(JSONArray elementsJSON) {
		HashMap<Integer, JSONObject> hashMap = new HashMap<Integer, JSONObject>();	
		for (int i = 0; i < elementsJSON.length(); ++i) {
			try {
				JSONObject missile = elementsJSON.getJSONObject(i);
				int id = missile.getInt("ID");
				hashMap.put(id, missile);
			} catch (Exception ex) {
				Log.e("Exception", ex.getMessage());
			}
		}

		for (Element element : elements)
			if (hashMap.containsKey(element.getID()))
				element.deserialize(hashMap.get(element.getID()));
	}
}
