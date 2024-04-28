package com.example.accidentreportsitp;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Locale;


public class sergeantInfo extends AppCompatActivity {

    EditText divisionEditText, sergeantNameEditText, sergeantRankEditText, sergeantIdEditText;

    TextView showdate,showtime,back,next;
    private Button languageButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sergeant_info);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);

        divisionEditText = findViewById(R.id.division);
        sergeantNameEditText = findViewById(R.id.sergeantName);
        sergeantRankEditText = findViewById(R.id.sergeantRank);
        sergeantIdEditText = findViewById(R.id.sergeantId);
        showdate = findViewById(R.id.date);
        showtime = findViewById(R.id.time);

        restoreData();

        languageButton = findViewById(R.id.languageButton);

        showdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateDialog();
            }
        });
        showtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTimeDialog();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                Intent intent = new Intent(sergeantInfo.this,vehicleInfo.class);
                startActivity(intent);
            }
        });
        languageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle between English and Urdu
                SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String currentLanguage = sharedPreferences.getString("language", "en");
                String newLanguage = currentLanguage.equals("en") ? "ur" : "en";

                // Save the new language preference in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("language", newLanguage);
                editor.apply();

                // Restart the activity to apply the new language
                recreate();
            }
        });


    }
    private void restoreData() {
        divisionEditText.setText(sergent_info_controller.getDivision());
        sergeantNameEditText.setText(sergent_info_controller.getSergeantName());
        sergeantRankEditText.setText(sergent_info_controller.getSergeantRank());
        sergeantIdEditText.setText(sergent_info_controller.getSergeantId());
        showdate.setText(sergent_info_controller.getDate());
        showtime.setText(sergent_info_controller.getTime());
    }
    private void saveData() {
        sergent_info_controller.setData(
                divisionEditText.getText().toString(),
                sergeantNameEditText.getText().toString(),
                sergeantRankEditText.getText().toString(),
                sergeantIdEditText.getText().toString(),
                showdate.getText().toString(),
                showtime.getText().toString()
        );
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


    public void openDateDialog(){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                showdate.setText(String.valueOf(year) + "\\" + String.valueOf(month) + "\\" + String.valueOf(year));
            }
        },2023,1,14);
        dialog.show();
    }
    public void openTimeDialog() {
        // Get current time
        int hour = 15;
        int minute = 0;
        // Create a TimePickerDialog
        TimePickerDialog dialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Update the TextView with the selected time
                        showtime.setText(String.format("%02d:%02d", hourOfDay, minute));
                    }
                }, hour, minute, false);
        // Show the TimePickerDialog
        dialog.show();
    }
}