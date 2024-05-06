package com.example.accidentreportsitp;

import java.util.ArrayList;
import java.util.List;

public class vehicle_info_controller {
    private static List<car_info_controller> cars = new ArrayList<>();

    public static void addCar(car_info_controller carInfo) {
        cars.add(carInfo);
    }

    public static List<car_info_controller> getCars() {
        return cars;
    }

    public static car_info_controller getCar(int index) {
        if (index >= 0 && index < cars.size()) {
            return cars.get(index);
        }
        return null;
    }

    public static void clearCars() {
        cars.clear();
    }
}
