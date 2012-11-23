package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Controller controller = new Controller(this);
        DrawView drawView = new DrawView(this);
        drawView.setBackgroundColor(Color.WHITE);
        drawView.setController(controller);
        setContentView(R.layout.activity_main);
        FrameLayout upper = (FrameLayout) findViewById(R.id.frameLayout2);
        upper.addView(drawView);
        controller.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
