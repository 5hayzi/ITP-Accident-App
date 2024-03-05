package com.example.accidentreportsitp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Death_screen extends AppCompatActivity {

    private Spinner policeBoxSpinner;
    private Spinner hospitalBoxSpinner;
    String[] policeItems = {"Station1", "Station2"};
    String[] hospitalItems = {"hospital1", "hospital2"};// Assuming these are your spinner items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.death_and_injury); // Ensure this layout file exists and is correctly named

        policeBoxSpinner = findViewById(R.id.PoliceBox); // Ensure this ID exists in your layout file
        setUpPoliceSpinner();

        hospitalBoxSpinner = findViewById(R.id.hospitalbox); // Ensure this ID exists in your layout file
        setUphospitalSpinner();
    }

    private void setUpPoliceSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, policeItems);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        policeBoxSpinner.setAdapter(adapter);

        // Set the listener to receive events when a spinner item is selected
        policeBoxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                 String selectedItem = policeItems[position];
                Toast.makeText(Death_screen.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                // Here, you can handle the selection (e.g., display a Toast or update the UI)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Interface callback for when nothing is selected
            }
        });
    }
    private void setUphospitalSpinner() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hospitalItems);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        hospitalBoxSpinner.setAdapter(adapter);

        // Set the listener to receive events when a spinner item is selected
        hospitalBoxSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // An item was selected. You can retrieve the selected item using
                String selectedItem = hospitalItems[position];
                Toast.makeText(Death_screen.this, "Selected: " + selectedItem, Toast.LENGTH_SHORT).show();
                // Here, you can handle the selection (e.g., display a Toast or update the UI)
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Interface callback for when nothing is selected
            }
        });
    }
}
