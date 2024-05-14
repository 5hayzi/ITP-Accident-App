package com.example.accidentreportsitp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.net.Uri;
import androidx.annotation.Nullable;
import android.provider.MediaStore;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class vehicleInfo extends AppCompatActivity {

    private Button btnAddCar;
    TextView back, next;
    private LinearLayout layoutCarFields;
    private static final int REQUEST_IMAGE_PICK = 1;
    private Uri currentImageUri;
    private Map<View, Uri> carViewToImageUriMap = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        btnAddCar = findViewById(R.id.addCarButton);
        layoutCarFields = findViewById(R.id.carsLayout);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);
        btnAddCar.setOnClickListener(v -> addNewCarEntry());

        restoreData();

        next.setOnClickListener(v -> {
            saveAllCars();
            startActivity(new Intent(vehicleInfo.this, roadAndCause.class));
        });

        back.setOnClickListener(v -> startActivity(new Intent(vehicleInfo.this, sergeantInfo.class)));
    }

    private void addNewCarEntry() {
        View carEntryView = getLayoutInflater().inflate(R.layout.car_entry, null);
        Button cameraButton = carEntryView.findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(v -> {
            openCameraOrGallery();
            currentImageUri = null; // Reset current URI
            carViewToImageUriMap.put(carEntryView, null); // Store with null initially
        });
        layoutCarFields.addView(carEntryView);
    }

    private void saveAllCars() {
        List<car_info_controller> cars = new ArrayList<>();
        for (int i = 0; i < layoutCarFields.getChildCount(); i++) {
            View carEntryView = layoutCarFields.getChildAt(i);
            EditText carRegNo = carEntryView.findViewById(R.id.carRegno);
            Spinner carType = carEntryView.findViewById(R.id.carTypeSpinner);
            EditText driverName = carEntryView.findViewById(R.id.driverName);
            EditText driverAge = carEntryView.findViewById(R.id.driverAge);
            RadioGroup driverGender = carEntryView.findViewById(R.id.driverGenderRadioGroup);
            RadioGroup driverLicense = carEntryView.findViewById(R.id.driverLicenseRadioGroup);
            RadioGroup seatBelt = carEntryView.findViewById(R.id.seatBeltRadioGroup);
            RadioGroup helmet = carEntryView.findViewById(R.id.helmetRadioGroup);

            String registrationNumber = carRegNo.getText().toString();
            String type = carType.getSelectedItem().toString();
            String name = driverName.getText().toString();
            String age = driverAge.getText().toString();

            Uri imageUri = carViewToImageUriMap.get(carEntryView);

            car_info_controller carInfo = new car_info_controller(registrationNumber, type, name, age, null);  // Assume imageUri handled
            if (!cars.contains(carInfo)) {
                cars.add(carInfo);
            }
        }

        vehicle_info_controller.addVehicle(cars); // Save the list of cars as a vehicle
    }
    private void restoreData() {
        layoutCarFields.removeAllViews();
        List<List<car_info_controller>> allVehicles = vehicle_info_controller.getAllVehicles();
        for (List<car_info_controller> vehicleCars : allVehicles) {
            for (car_info_controller carInfo : vehicleCars) {
                View carEntryView = getLayoutInflater().inflate(R.layout.car_entry, null);
                layoutCarFields.addView(carEntryView);

                EditText carRegNo = carEntryView.findViewById(R.id.carRegno);
                Spinner carType = carEntryView.findViewById(R.id.carTypeSpinner);
                EditText driverName = carEntryView.findViewById(R.id.driverName);
                EditText driverAge = carEntryView.findViewById(R.id.driverAge);
                RadioGroup driverGender = carEntryView.findViewById(R.id.driverGenderRadioGroup);
                RadioGroup driverLicense = carEntryView.findViewById(R.id.driverLicenseRadioGroup);
                RadioGroup seatBelt = carEntryView.findViewById(R.id.seatBeltRadioGroup);
                RadioGroup helmet = carEntryView.findViewById(R.id.helmetRadioGroup);

                carRegNo.setText(carInfo.getRegistrationNumber());
                carType.setSelection(getIndex(carType, carInfo.getCarType()));
                driverName.setText(carInfo.getDriverName());
                driverAge.setText(carInfo.getDriverAge());
            }
        }
    }

    private void openCameraOrGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (currentImageUri == null) {
                // This is the first image being selected
                currentImageUri = selectedImageUri;
            }
            // Update the last added entry with the URI
            View lastAddedView = layoutCarFields.getChildAt(layoutCarFields.getChildCount() - 1);
            carViewToImageUriMap.put(lastAddedView, selectedImageUri);
        } else {
            Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
        }
    }

    private int getIndex(Spinner spinner, String item) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(item)) {
                return i;
            }
        }
        return 0;
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
