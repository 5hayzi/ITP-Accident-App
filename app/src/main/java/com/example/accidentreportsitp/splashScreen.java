package com.example.accidentreportsitp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        // Delayed handler to start the next activity after 2500 milliseconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the next activity
                Intent intent = new Intent(splashScreen.this, sergeantInfo.class);
                startActivity(intent);
                finish(); // Finish the splash activity
            }
        }, 2500);

        // Button click listener to switch language

    }

}
