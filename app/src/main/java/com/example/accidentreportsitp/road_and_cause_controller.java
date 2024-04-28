package com.example.accidentreportsitp;

public class road_and_cause_controller {
    private static String description, faultNumber, damageVehicle, accidentCause, collisionCause, accidentPlace, roadName;
    public static void setData(String desc, String faultNum, String damage, String accident, String collision, String place, String road) {
        description = desc;
        faultNumber = faultNum;
        damageVehicle = damage;
        accidentCause = accident;
        collisionCause = collision;
        accidentPlace = place;
        roadName = road;
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
    public static String getAccidentCause() {
        return accidentCause;
    }
    public static String getCollisionCause() {
        return collisionCause;
    }
    public static String getAccidentPlace() {
        return accidentPlace;
    }
    public static String getRoadName() {
        return roadName;
    }
}
