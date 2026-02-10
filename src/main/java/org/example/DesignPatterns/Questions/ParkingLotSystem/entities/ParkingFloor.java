package org.example.DesignPatterns.Questions.ParkingLotSystem.entities;

import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingFloor {
    private int floor;
    private List<ParkingSpot> spots;

    public ParkingFloor(int floor) {
        this.floor = floor;
        this.spots = new ArrayList<>();
    }

    public int getFloor() {
        return floor;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle){
        return this.spots.stream().filter((it) -> it.getSize() == vehicle.getSize() && it.isAvailable()).toList().stream().findAny();
    }

    public void addSpot(ParkingSpot spot){
        this.spots.add(spot);
    }
    public void displayAvailability(){
        this.spots.stream().filter(ParkingSpot::isAvailable).forEach((c) -> {
            System.out.println("Spot with id" + c.getId() + " Empty.");
        });
    }
}
