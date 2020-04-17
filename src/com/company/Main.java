package com.company;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        CarStore carStore = new CarStore();
        LinkedList<Car> cars = carStore.getCars();

        cars.forEach(car -> {
            System.out.println(car.brand + " " + car.model + " " + car.price);
        });

        carStore.deleteCar(15);
    }
}
