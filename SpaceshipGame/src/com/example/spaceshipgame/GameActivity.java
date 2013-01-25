package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class GameActivity extends Activity {
	Controller controller;
	
    @Override
    public void onStart() {
		super.onStart();

        setContentView(R.layout.game_activity);
        Intent intent = getIntent();
        String host = intent.getStringExtra("host");
        controller = new Controller(this, host);
        GameView gameView = (GameView) findViewById(R.id.gameView);
        gameView.setController(controller);
    }

    @Override
    public void onStop() {
    	super.onStop();
    	controller.onStop();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
