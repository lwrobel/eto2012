package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Controller controller = new Controller(this);
        DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        drawView.setController(controller);
        setContentView(drawView);      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
