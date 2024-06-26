package com.example.accidentreportsitp;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLogTags;
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

    private EditText Description, faultNumber, damageVehicle, accidentPlace;
    private Spinner accidentCause, collisionCause, roadName;
    TextView back,next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.road_and_cause);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);

        Description = findViewById(R.id.RoadAndCause);
        faultNumber = findViewById(R.id.faultNumber);
        damageVehicle = findViewById(R.id.damageVehicle);
        accidentCause = findViewById(R.id.accidentCauseBox);
        collisionCause = findViewById(R.id.collisionCauseBox);
        accidentPlace = findViewById(R.id.accidentPlace);
        roadName = findViewById(R.id.roadNameBox);

        // Restore data from Controller class
        Description.setText(road_and_cause_controller.getDescription());
        faultNumber.setText(road_and_cause_controller.getFaultNumber());
        damageVehicle.setText(road_and_cause_controller.getDamageVehicle());
        accidentPlace.setText(road_and_cause_controller.getAccidentPlace());
        accidentCause.setSelection(getIndex(accidentCause, road_and_cause_controller.getAccidentCause()));
        collisionCause.setSelection(getIndex(collisionCause, road_and_cause_controller.getCollisionCause()));
        roadName.setSelection(getIndex(roadName, road_and_cause_controller.getRoadName()));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                road_and_cause_controller.setData(
                        Description.getText().toString(),
                        faultNumber.getText().toString(),
                        damageVehicle.getText().toString(),
                        accidentCause.getSelectedItem().toString(),
                        collisionCause.getSelectedItem().toString(),
                        accidentPlace.getText().toString(),
                        roadName.getSelectedItem().toString()
                );
                Intent intent = new Intent(roadAndCause.this,Death_screen.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(roadAndCause.this,vehicleInfo.class);
                startActivity(intent);
            }
        });

    }

    private int getIndex(Spinner spinner, String item) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return 0;
    }
    protected void attachBaseContext(Context newBase) {
        // Retrieve the saved language preference from SharedPreferences
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");

        // Set the locale based on the selected language
        Locale locale = new Locale(language);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        Context context = newBase.createConfigurationContext(configuration);
        super.attachBaseContext(context);
    }
}

