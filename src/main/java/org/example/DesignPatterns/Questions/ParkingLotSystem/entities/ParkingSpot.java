package org.example.DesignPatterns.Questions.ParkingLotSystem.entities;

import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.Vehicle;
import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.VehicleSize;

public class ParkingSpot {
    private String id;
    private VehicleSize size;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String id, VehicleSize size) {
        this.id = id;
        this.size = size;
        this.isOccupied = false;
        this.parkedVehicle = null;
    }

    public String getId() {
        return id;
    }

    public boolean canFitVehicle(Vehicle vehicle){
        return vehicle.getSize() == size;
    }

    public void parkVehicle(Vehicle vehicle){
        this.parkedVehicle = vehicle;
        this.isOccupied = true;
    }

    public void unParkVehicle(){
        this.parkedVehicle = null;
        this.isOccupied = false;

    }
    public boolean isAvailable(){
        return !isOccupied;
    }

    public VehicleSize getSize() {
        return size;
    }
}
