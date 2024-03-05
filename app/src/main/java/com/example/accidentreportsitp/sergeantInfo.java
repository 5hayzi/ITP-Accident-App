package com.example.accidentreportsitp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class sergeantInfo extends AppCompatActivity {

    TextView showdate,showtime,back,next;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sergeant_info);

        showdate = findViewById(R.id.date);
        showtime = findViewById(R.id.time);
        back = findViewById(R.id.btnprev);
        next = findViewById(R.id.btnnxt);

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

        next = findViewById(R.id.btnnxt);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sergeantInfo.this,vehicleInfo.class);
                startActivity(intent);
            }
        });


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
                }, hour, minute, true);

        // Show the TimePickerDialog
        dialog.show();
    }






}