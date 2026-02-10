package org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.fee;

import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.Ticket;

import java.util.concurrent.TimeUnit;

public class FixedRateStrategy implements FeeStrategy {
    private double hourlyRate;

    public FixedRateStrategy(double hourlyRate){
        this.hourlyRate = hourlyRate;
    }
    @Override
    public double calculateFee(Ticket ticket) {
        long hours  = TimeUnit.MILLISECONDS.toHours(ticket.getEndTime() - ticket.getStartTime());
        System.out.println("Vehicle with " + ticket.getVehicle().getVehicleNumber() + " was parked for " + hours + "Hours.");

        long fee = (long) (hours*hourlyRate);
        System.out.println("Vehicle with " + ticket.getVehicle().getVehicleNumber() + " was parked for " + hours + "Hours." + "Fee" + fee);
        return fee;
    }
}
