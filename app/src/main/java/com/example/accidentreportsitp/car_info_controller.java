package com.example.accidentreportsitp;

import android.net.Uri;
public class car_info_controller {
        private static String registrationNumber, carType, driverName, driverAge;
        private static boolean hasLicense, seatBeltUsed, helmetUsed, driverGender;
        private static Uri imageUri;
        private static int Count;

        public car_info_controller(String RegistrationNumber, String Type, String Name, String Age, Uri image) {
            registrationNumber = RegistrationNumber;
            carType = Type;
            driverName = Name;
            driverAge = Age;
            driverGender = driverGender;
            hasLicense = hasLicense;
            seatBeltUsed = seatBeltUsed;
            helmetUsed = helmetUsed;
            imageUri = image;
        }
        public static void setData(car_info_controller carInfo) {
            String Number = carInfo.getRegistrationNumber();
            String Type = carInfo.getCarType();
            String Name = carInfo.getDriverName();
            String Age = carInfo.getDriverAge();
            boolean Gender = carInfo.getDriverGender();
            boolean icense = carInfo.getHasLicense();
            boolean seatBelt = carInfo.getSeatBeltUsed();
            boolean helmet = carInfo.getHelmetUsed();
            Uri image = carInfo.getImageUri();
        vehicle_info_controller.addCar(carInfo);
    }
        public static void setCount(int count){
        Count = count;
        }
        public static int getCount(){return Count;}
        public static String getRegistrationNumber(){return registrationNumber;}
        public static String getCarType(){return carType;}
        public static String getDriverName(){return driverName;}
        public static String getDriverAge(){return driverAge;}
        public static Boolean getDriverGender(){return driverGender;}
        public static boolean getHasLicense(){return hasLicense;}
        public static boolean getSeatBeltUsed(){return seatBeltUsed;}
        public static boolean getHelmetUsed(){return helmetUsed;}
        public static Uri getImageUri(){return imageUri;}


}
