package com.example.accidentreportsitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.Context;
import java.util.Locale;

public class roadAndCause extends AppCompatActivity {

    private EditText roadAndCauseEditText;
    private EditText faultNumberEditText;
    private EditText damageVehicleEditText;
    private EditText accidentPlaceEditText;
    private Spinner accidentCauseBox;
    private Spinner roadNameBox;
    TextView back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.road_and_cause);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);
        // Make sure the layout name matches your XML file name

        // Initialize your UI components here
        roadAndCauseEditText = findViewById(R.id.RoadAndCause);
        faultNumberEditText = findViewById(R.id.faultNumber);
        damageVehicleEditText = findViewById(R.id.damageVehicle);
        accidentPlaceEditText = findViewById(R.id.accidentPlace);
        accidentCauseBox = findViewById(R.id.accidentCauseBox);
        roadNameBox = findViewById(R.id.roadNameBox);

        // Here you can set listeners or any initial setup for your views.
        // For example, if you have a button to submit the form, initialize it here and set its onClickListener.

        // Example setup for a Spinner (dropdown menu)
        // setupSpinner();

        // Other initialization code can go here
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(roadAndCause.this,Death_screen.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    // You might want to create additional methods to setup your views, such as setting up a spinner adapter.
    // private void setupSpinner() { ... }

    protected void attachBaseContext(Context newBase) {
        // Retrieve the saved language preference from SharedPreferences
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");

        // Set the locale based on the selected language
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        Context context = newBase.createConfigurationContext(configuration);
        super.attachBaseContext(context);}
}

