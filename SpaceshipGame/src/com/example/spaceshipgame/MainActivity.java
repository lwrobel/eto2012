package com.example.spaceshipgame;

import java.util.ArrayList;

import com.example.spaceshipgame.model.Element;
import com.example.spaceshipgame.model.Missle;
import com.example.spaceshipgame.model.Spaceship;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ArrayList <Element> elementList = new ArrayList <Element>();
        for (int i=1; i<=10; ++i) 
        	elementList.add(new Missle());
        
        for (int i=1; i<=5; ++i)
        	elementList.add(new Spaceship());
        
        DrawView drawView = new DrawView(this);
        drawView.setElementList(elementList);
        drawView.setBackgroundColor(Color.WHITE);
        setContentView(drawView);      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
