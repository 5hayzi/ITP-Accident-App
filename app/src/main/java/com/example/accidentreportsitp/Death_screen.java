package com.example.accidentreportsitp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.Context;
import java.util.Locale;

public class Death_screen extends AppCompatActivity {

    private EditText deathDescp, injuryDescp, deathNo, injuryNo, dutyOfficer;
    private Spinner policeBoxSpinner, hospitalBoxSpinner;

    private String FIROption, CompromiseOption;
    private RadioGroup FIRBox, compromiseBox;
    private RadioButton registeredButton, notRegisteredButton, handedOverButton, YesBtn, NoBtn;

    TextView back,submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.death_and_injury);

        deathNo = findViewById(R.id.DeathNumber);
        deathDescp = findViewById(R.id.death_Description);
        injuryNo = findViewById(R.id.InjuryNumber);
        injuryDescp = findViewById(R.id.injury_Description);
        dutyOfficer = findViewById(R.id.DutyOfficer);
        policeBoxSpinner = findViewById(R.id.PoliceBox);

        hospitalBoxSpinner = findViewById(R.id.hospitalbox);
        back = findViewById(R.id.btnprev);
        submit = findViewById(R.id.Submit);

        FIRBox = findViewById(R.id.FIRBox);
        registeredButton = findViewById(R.id.RegisteredBox);
        notRegisteredButton = findViewById(R.id.notRegisteredBox);
        handedOverButton = findViewById(R.id.handedBox);

        compromiseBox = findViewById(R.id.CompromiseBox);
        YesBtn = findViewById(R.id.YesBox);
        NoBtn = findViewById(R.id.NoBox);

        RetrieveData();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Death_screen.this,roadAndCause.class);
                startActivity(intent);
            }
        });

        FIRBox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.RegisteredBox ) {
                    FIROption = registeredButton.getText().toString();
                } else if (checkedId == R.id.notRegisteredBox ) {
                    FIROption = notRegisteredButton.getText().toString();
                } else if (checkedId == R.id.handedBox ) {
                    FIROption = handedOverButton.getText().toString();
                }
            }
        });

        compromiseBox.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.YesBox ) {
                    CompromiseOption = YesBtn.getText().toString();
                } else if (checkedId == R.id.NoBox ) {
                    CompromiseOption = NoBtn.getText().toString();
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                death_screen_controller.set_data(
                        deathDescp.getText().toString(),
                        injuryDescp.getText().toString(),
                        dutyOfficer.getText().toString(),
                        policeBoxSpinner.getSelectedItem().toString(),
                        hospitalBoxSpinner.getSelectedItem().toString(),
                        deathNo.getText().toString(),
                        injuryNo.getText().toString(),
                        FIROption,
                        CompromiseOption
                        );
            }
        });

    }
    public void RetrieveData(){
                deathDescp.setText(death_screen_controller.getDeath_descp());
                injuryDescp.setText(death_screen_controller.getInjury_descp());
                dutyOfficer.setText(death_screen_controller.getOfficer_duty());
                policeBoxSpinner.setSelection(getIndex(policeBoxSpinner, death_screen_controller.getPolice()));
                hospitalBoxSpinner.setSelection(getIndex(hospitalBoxSpinner, death_screen_controller.getHospital()));
                deathNo.setText(death_screen_controller.getDeath_No());
                injuryNo.setText(death_screen_controller.getInjury_No());
                String fir = death_screen_controller.getFIR();
                if(fir != null){
                    if ( fir.equals("Registered")){
                        registeredButton.setChecked(true);
                    } else if (fir.equals("Not Registered")) {
                        notRegisteredButton.setChecked(true);
                    } else if (fir.equals("Handed Over")) {
                        handedOverButton.setChecked(true);
                    }
                }

                String comp = death_screen_controller.getCompromise();
                if(comp != null){
                if( comp.equals("Yes")){
                    YesBtn.setChecked(true);
                } else if (comp.equals("No")) {
                    NoBtn.setChecked(true);
                }}

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
