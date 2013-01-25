package com.example.spaceshipgame;

import com.example.spaceshipgame.controller.Controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
    @Override
    public void onStart() {
		super.onStart();

        setContentView(R.layout.main_activity);

        final Button button = (Button) findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent myIntent = new Intent(getBaseContext(), GameActivity.class);
            	EditText hostEditText = (EditText)findViewById(R.id.hostEditText);
            	myIntent.putExtra("host", hostEditText.getText().toString());
            	startActivity(myIntent);
            }
        });
    }
}
