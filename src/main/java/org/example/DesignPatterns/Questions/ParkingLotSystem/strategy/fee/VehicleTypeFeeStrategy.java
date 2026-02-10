package org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.fee;

import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.Ticket;
import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.VehicleSize;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class VehicleTypeFeeStrategy implements FeeStrategy {

    private HashMap<VehicleSize, Double> costPerVehicleType;

    public VehicleTypeFeeStrategy(HashMap<VehicleSize, Double> costPerVehicleType){
        this.costPerVehicleType = costPerVehicleType;
    }
    @Override
    public double calculateFee(Ticket ticket) {
        long diff = ticket.getEndTime() - ticket.getStartTime();
        double hourlyRate = costPerVehicleType.get(ticket.getVehicle().getSize());
        return TimeUnit.MILLISECONDS.toHours(diff)*hourlyRate;
    }
}
