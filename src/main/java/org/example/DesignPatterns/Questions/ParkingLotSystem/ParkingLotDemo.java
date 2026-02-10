package org.example.DesignPatterns.Questions.ParkingLotSystem;

import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.ParkingFloor;
import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.ParkingLotSystem;
import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.ParkingSpot;
import org.example.DesignPatterns.Questions.ParkingLotSystem.entities.Ticket;
import org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.fee.VehicleTypeFeeStrategy;
import org.example.DesignPatterns.Questions.ParkingLotSystem.strategy.parking.NearestParkingStrategy;
import org.example.DesignPatterns.Questions.ParkingLotSystem.vehicle.*;

import java.util.HashMap;
import java.util.Optional;

public class ParkingLotDemo {
    public static void main(String[] args) {
        ParkingLotSystem parkingLot = ParkingLotSystem.getInstance();
        parkingLot.setParkingStrategy(new NearestParkingStrategy());
        // 1. Initialize the parking lot with floors and spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        HashMap<VehicleSize, Double> costPerVehicleType = new HashMap<>();
        costPerVehicleType.put(VehicleSize.SMALL, 5.0);
        costPerVehicleType.put(VehicleSize.MEDIUM, 8.0);
        costPerVehicleType.put(VehicleSize.LARGE, 15.0);

        parkingLot.setFeeStrategy(new VehicleTypeFeeStrategy(costPerVehicleType));

        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-123");
        Vehicle car = new Car("C-456");
        Vehicle truck = new Truck("T-789");

        Optional<Ticket> bikeTicketOpt = parkingLot.parkVehicle(bike);

        Optional<Ticket> carTicketOpt = parkingLot.parkVehicle(car);

        Optional<Ticket> truckTicketOpt = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 3. Simulate another car entry (should go to floor 2)
        Vehicle car2 = new Car("C-999");
        Optional<Ticket> car2TicketOpt = parkingLot.parkVehicle(car2);

        // 4. Simulate a vehicle entry that fails (no available spots)
        Vehicle bike2 = new Bike("B-000");
        Optional<Ticket> failedBikeTicketOpt = parkingLot.parkVehicle(bike2);

        // 5. Simulate vehicle exits and fee calculation
        System.out.println("\n--- Vehicle Exits ---");

        if (carTicketOpt.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unparkVehicle(car.getVehicleNumber());
            feeOpt.ifPresent(fee -> System.out.printf("Car C-456 unparked. Fee: $%.2f\n", fee));
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        System.out.println("Earning is: " +  parkingLot.getEarning());
    }
}
