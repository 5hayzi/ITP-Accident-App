package com.example.accidentreportsitp;

public class road_and_cause_controller {
    private static String description;
    private static String faultNumber;
    private static String damageVehicle;

    public static void setData(String desc, String faultNum, String damage) {
        description = desc;
        faultNumber = faultNum;
        damageVehicle = damage;
    }

    public static String getDescription() {
        return description;
    }

    public static String getFaultNumber() {
        return faultNumber;
    }

    public static String getDamageVehicle() {
        return damageVehicle;
    }
}
