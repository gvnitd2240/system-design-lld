# Designing a Parking Lot System

This project demonstrates the **Low Level Design (LLD)** of a Parking Lot System using object-oriented principles and common design patterns. The goal is to model a real-world parking lot that supports multiple vehicle types, multiple floors, and concurrent access.

---

## Requirements

- The parking lot should have **multiple levels**, each level with a certain number of parking spots.
- The parking lot should support different types of vehicles:
    - Cars
    - Motorcycles
    - Trucks
- Each parking spot should be able to accommodate a **specific type of vehicle**.
- The system should:
    - Assign a parking spot to a vehicle upon entry
    - Release the parking spot when the vehicle exits
- The system should track the **availability of parking spots** and provide real-time information.
- The system should handle **multiple entry and exit points**.
- The system should support **concurrent access**.

---

## Class Diagram

The following class diagram illustrates the relationships between the core components of the Parking Lot system:
<p align="center">
  <a href="https://raw.githubusercontent.com/ashishps1/awesome-low-level-design/main/class-diagrams/parkinglot-class-diagram.png" target="_blank">
    <img src="https://raw.githubusercontent.com/ashishps1/awesome-low-level-design/main/class-diagrams/parkinglot-class-diagram.png" width="850"/>
  </a>
</p>
---

## Classes, Interfaces, and Enumerations

### ParkingLot
- Follows the **Singleton Pattern** to ensure only one instance exists.
- Maintains a list of parking levels.
- Provides APIs to park and unpark vehicles.

### Level
- Represents a single level in the parking lot.
- Contains a list of parking spots.
- Handles parking and unparking logic at the level level.

### ParkingSpot
- Represents an individual parking spot.
- Tracks availability status.
- Maintains the reference to the currently parked vehicle.

### Vehicle
- Abstract base class for all vehicle types.
- Extended by:
    - `Car`
    - `Motorcycle`
    - `Truck`

### VehicleType (Enum)
- Defines the supported vehicle types:
    - CAR
    - MOTORCYCLE
    - TRUCK

---

## Concurrency Handling

- Multi-threading is handled using the `synchronized` keyword.
- Critical sections such as parking and unparking operations are synchronized to ensure **thread safety**.
- Prevents race conditions during concurrent entry and exit operations.

---

## Design Patterns Used

### Singleton Pattern
- Ensures a single instance of the `ParkingLot` class across the system.

### Factory Pattern (Optional Extension)
- Can be used to create vehicle objects based on input type.

### Observer Pattern (Optional Extension)
- Can be used to notify customers when parking spots become available.

---

## Demo / Driver Class

- The `Main` (or `ParkingLotDemo`) class demonstrates:
    - Parking lot initialization
    - Vehicle entry
    - Spot allocation
    - Vehicle exit
    - Spot release
