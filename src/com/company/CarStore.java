package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Offers methods to fetch, delete, update and insert cars. Also stores a car list.
 */
public class CarStore {
    private DatabaseConnector dbConnector = new DatabaseConnector();
    public LinkedList<Car> cars = new LinkedList<>();

    public LinkedList<Car> getCars() {
        if (cars.isEmpty()) {
            fetchCars();
        }

        return cars;
    }

    private void fetchCars() {
        // delete old entries
        cars.clear();

        ResultSet result = dbConnector.fetchData("SELECT * FROM car");
        if (result == null) {
            System.out.println("could not fetch cars");
            return;
        }

        try {
            while (result.next()) {
                String brand = result.getString("brand");
                String model = result.getString("model");
                int price = result.getInt("price");
                cars.add(new Car(brand, model, price));
            }
        } catch (SQLException e) {
            System.out.println("error while parsing cars");
            System.out.println(e.getMessage());
        } finally {
            // Unfortunately I have to close the connection here. Preferred would have been in the
            // dbConnector.fetchData() function. But I have to use the ResultSet here to create the
            // car objects. I couldn't use the ResultSet when the connections were closed already.
            dbConnector.closeConnection();
        }
    }

    public void deleteCar (int carId) {
        boolean wasDeleted = dbConnector.delete("DELETE FROM car WHERE id = " + carId);
        if (wasDeleted) {
            System.out.println("need to update my data");
            fetchCars();
        }
    }
}
