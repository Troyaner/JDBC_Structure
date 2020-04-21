package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CarStore carStore = CarStore.getInstance();
        ArrayList<Car> cars = carStore.getCars();

        cars.forEach(car -> {
            System.out.println(car.id + " - " + car.brand + " " + car.model + " " + car.price);
        });

        carStore.deleteCar(16);
    }
}
