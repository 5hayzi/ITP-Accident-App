package com.example.accidentreportsitp;

import java.util.ArrayList;
import java.util.List;

public class vehicle_info_controller {
    private static List<List<car_info_controller>> vehicles = new ArrayList<>();

    public static void addVehicle(List<car_info_controller> carList) {
        vehicles.add(carList);
    }

    public static List<List<car_info_controller>> getAllVehicles() {
        return vehicles;
    }

    public static void clearVehicles() {
        vehicles.clear();
    }
}
