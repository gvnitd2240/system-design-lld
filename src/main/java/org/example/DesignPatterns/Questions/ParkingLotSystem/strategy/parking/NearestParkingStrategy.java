package org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.parking;

import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.ParkingFloor;
import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.ParkingSpot;
import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public class NearestParkingStrategy implements ParkingStrategy {
    @Override
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle) {
        for (ParkingFloor floor : parkingFloors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();

    }
}
