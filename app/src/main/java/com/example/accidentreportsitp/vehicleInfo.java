package com.example.accidentreportsitp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class vehicleInfo extends AppCompatActivity {

    private Button btnAddCar;
    private LinearLayout layoutCarFields;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        btnAddCar = findViewById(R.id.addCarButton);
        layoutCarFields = findViewById(R.id.carsLayout);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inflate the layout for a single car entry
                View carEntryView = getLayoutInflater().inflate(R.layout.car_entry, null);

                // Find the EditText fields within the inflated layout
                EditText carRegNo = carEntryView.findViewById(R.id.carRegno);
                Spinner carType = carEntryView.findViewById(R.id.carTypeSpinner);
                EditText driverName = carEntryView.findViewById(R.id.driverName);
                EditText driverAge = carEntryView.findViewById(R.id.driverAge);
                RadioGroup driverGenderRadioGroup = carEntryView.findViewById(R.id.driverGenderRadioGroup);
                RadioGroup driverLicenseRadioGroup = carEntryView.findViewById(R.id.driverLicenseRadioGroup);
                RadioGroup seatBeltRadioGroup = carEntryView.findViewById(R.id.seatBeltRadioGroup);
                RadioGroup helmetRadioGroup = carEntryView.findViewById(R.id.helmetRadioGroup);

                // Add the inflated layout to the parent layout
                layoutCarFields.addView(carEntryView);
            }
        });
    }
}
