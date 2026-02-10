package org.example.DesignPatterns.Questions.ParkingLotSystem.entities;

import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.Vehicle;

import java.util.UUID;

public class Ticket {

    private UUID id;
    private Vehicle vehicle;
    private ParkingSpot spot;

    private Long startTime;
    private Long endTime;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.id = UUID.randomUUID();
        this.startTime = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public void setEndTime() {
        this.endTime = System.currentTimeMillis() + 3600000;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setSpot(ParkingSpot spot) {
        this.spot = spot;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }
}
