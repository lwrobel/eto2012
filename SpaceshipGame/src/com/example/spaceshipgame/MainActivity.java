package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        Controller controller = new Controller(this);
        GameView gameView = (GameView) findViewById(R.id.gameView);
        gameView.setController(controller);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
