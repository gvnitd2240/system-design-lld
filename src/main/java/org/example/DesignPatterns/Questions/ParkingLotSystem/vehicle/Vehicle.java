package org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle;

public abstract class Vehicle {
    private VehicleSize size;
    private String vehicleNumber;

    public Vehicle(VehicleSize size, String vehicleNumber) {
        this.size = size;
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleSize getSize() {
        return size;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}
