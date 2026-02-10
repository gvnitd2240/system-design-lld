package org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.fee;

import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.Ticket;

public interface FeeStrategy {
    public double calculateFee(Ticket ticket);
}
