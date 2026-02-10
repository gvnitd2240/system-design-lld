package org.example.DesignPatterns.Questions.ParkingLotSystem.entities;

import org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.fee.FeeStrategy;
import org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.parking.ParkingStrategy;
import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ParkingLotSystem {
    private static ParkingLotSystem instance;
    private ParkingStrategy parkingStrategy;
    private List<ParkingFloor> parkingFloors;

    private HashMap<String, Ticket> activeTickets;

    private FeeStrategy feeStrategy;
    private double earning;

    private ParkingLotSystem(){
        this.parkingFloors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.earning = 0;
    }

    public double getEarning() {
        return earning;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public static ParkingLotSystem getInstance(){
        if(instance == null){
            synchronized (ParkingLotSystem.class){
                if(instance == null){
                    instance = new ParkingLotSystem();
                }
            }
        }

        return instance;
    }
    public void addFloor(ParkingFloor floor){
        this.parkingFloors.add(floor);
    }

    public Optional<Ticket> parkVehicle(Vehicle vehicle){
        Optional<ParkingSpot> availableSpot = parkingStrategy.findSpot(parkingFloors,vehicle);

        if(availableSpot.isPresent()){
            ParkingSpot spot = availableSpot.get();
            spot.parkVehicle(vehicle);
            Ticket ticket = new Ticket(vehicle, spot);
            activeTickets.put(vehicle.getVehicleNumber(), ticket);
            System.out.printf("%s parked at %s. Ticket: %s\n", vehicle.getVehicleNumber(), spot.getId(), ticket.getId());
            return Optional.of(ticket);
        }

        System.out.println("No available spot for " + vehicle.getVehicleNumber());
        return Optional.empty();
    }

    public Optional<Double> unparkVehicle(String licenceNumber){
        Ticket ticket = activeTickets.remove(licenceNumber);

        if(ticket == null){
            System.out.println("Ticket not found");
            return Optional.empty();
        }

        ticket.setEndTime();
        ticket.getSpot().unParkVehicle();

        Double parkingFee = feeStrategy.calculateFee(ticket);

        earning+=parkingFee;

        return Optional.of(parkingFee);
    }
}
