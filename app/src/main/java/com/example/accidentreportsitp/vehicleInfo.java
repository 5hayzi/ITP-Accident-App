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
import java.util.Locale;
import android.net.Uri;
import androidx.annotation.Nullable;
import android.provider.MediaStore;
import android.widget.Toast;

public class vehicleInfo extends AppCompatActivity {

    private Button btnAddCar;
    TextView back,next;
    private LinearLayout layoutCarFields;
    private Button camerabtn;
    private static final int REQUEST_IMAGE_PICK = 1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_info);

        btnAddCar = findViewById(R.id.addCarButton);
        layoutCarFields = findViewById(R.id.carsLayout);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);

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
                Button camerabtn = carEntryView.findViewById(R.id.cameraButton);
                // Add the inflated layout to the parent layout
                layoutCarFields.addView(carEntryView);

                camerabtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Launch camera or gallery intent
                        openCameraOrGallery();
                    }
                });
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vehicleInfo.this,roadAndCause.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vehicleInfo.this,sergeantInfo.class);
                startActivity(intent);
            }
        });
    }
    private void openCameraOrGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_IMAGE_PICK:
                if (resultCode == RESULT_OK && data != null) {
                    // Handle the selected image here
                    Uri selectedImageUri = data.getData();
                    // Do something with the selected image URI, such as displaying it in an ImageView or saving it to storage
                } else {
                    // Handle case where image selection failed
                    Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
                }
                break;
        }
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