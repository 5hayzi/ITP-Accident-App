package com.example.accidentreportsitp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Death_screen extends AppCompatActivity {

    private Spinner policeBoxSpinner;
    private Spinner hospitalBoxSpinner;
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
}
