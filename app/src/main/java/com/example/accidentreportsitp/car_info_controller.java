package com.example.accidentreportsitp;

import android.net.Uri;

public class car_info_controller {
    private String registrationNumber, carType, driverName, driverAge;
    private boolean hasLicense, seatBeltUsed, helmetUsed, driverGender;
    private Uri imageUri;

    public car_info_controller( String RegistrationNumber, String Type, String Name, String Age, Uri image) {

        registrationNumber = RegistrationNumber;
        carType = Type;
        driverName = Name;
        driverAge = Age;
        driverGender = driverGender; // Initialize driverGender based on your logic
        hasLicense = hasLicense; // Initialize hasLicense based on your logic
        seatBeltUsed = seatBeltUsed; // Initialize seatBeltUsed based on your logic
        helmetUsed = helmetUsed; // Initialize helmetUsed based on your logic
        imageUri = image;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCarType() {
        return carType;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public Boolean getDriverGender() {
        return driverGender;
    }

    public boolean getHasLicense() {
        return hasLicense;
    }

    public boolean getSeatBeltUsed() {
        return seatBeltUsed;
    }

    public boolean getHelmetUsed() {
        return helmetUsed;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
