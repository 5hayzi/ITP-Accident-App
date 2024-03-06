package com.example.accidentreportsitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.Context;
import java.util.Locale;

public class Death_screen extends AppCompatActivity {

    private Spinner policeBoxSpinner;
    private Spinner hospitalBoxSpinner;
    TextView back,next;
    String[] policeItems = {"AABPARA", "KOHSAR", "SECRETARIAT", "MARGALLA", "KARACHI COMPANY", "SHALIMAR", "GOLRA SHARIF", "RAMNA", "INDUSTRIAL AREA", "SABZI MANDI", "KHANA", "NOON", "SHAMAS COLONY", "TARNOL", "KORAL", "LOHI BHEER", "BHARAKAHU", "SHAHZAD TOWN", "BANI GALA", "SIHALA", "NILOR"};
    String[] hospitalItems = {"PIMS", "POLY CLINIC", "HOLY FAMILY", "AL MAROOF", "AL SHIFA", "QUAID E AZAM", "SERVICES HOSPITAL", "N.A."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.death_and_injury);

        policeBoxSpinner = findViewById(R.id.PoliceBox);
        setUpPoliceSpinner();

        hospitalBoxSpinner = findViewById(R.id.hospitalbox);
        setUphospitalSpinner();
        back = findViewById(R.id.btnprev);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpPoliceSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, policeItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        policeBoxSpinner.setAdapter(adapter);

        // Add hint to police spinner
        policeBoxSpinner.setPrompt("Select a Police Station");

        // Handle selection and reflect it back in the spinner box
        policeBoxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = policeItems[position];
                ((TextView) parentView.getChildAt(0)).setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Interface callback for when nothing is selected
            }
        });
    }

    private void setUphospitalSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hospitalItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalBoxSpinner.setAdapter(adapter);

        // Add hint to hospital spinner
        hospitalBoxSpinner.setPrompt("Select a Hospital");

        // Handle selection and reflect it back in the spinner box
        hospitalBoxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = hospitalItems[position];
                ((TextView) parentView.getChildAt(0)).setText(selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Interface callback for when nothing is selected
            }
        });
    }
    @Override
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
