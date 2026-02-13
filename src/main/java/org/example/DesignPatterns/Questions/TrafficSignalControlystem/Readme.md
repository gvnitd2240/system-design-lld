# 🚦 Designing Traffic Signal System

This project demonstrates the **Low Level Design (LLD)** of a configurable **Traffic Signal Management System** for an intersection.  
The system is designed using **object-oriented principles**, supports **automatic signal transitions**, and models real-world traffic behavior using the **State Design Pattern**.

It enables configurable timings, round-robin signal cycling, and manual override capabilities for flexible traffic control.

---

## 📌 Requirements

### Functional Requirements
- The system manages traffic lights for **multiple directions**:
    - NORTH
    - SOUTH
    - EAST
    - WEST
- Each direction has a traffic signal with states:
    - GREEN
    - YELLOW
    - RED
- Each direction and signal state has a **configurable duration**.
- Signals transition automatically in a **round-robin order**.
- The system supports **manual override** to force any direction to GREEN.
- The system allows adding:
    - New directions
    - New signal states
    - Custom timing strategies

### Non-Functional Requirements
- Reliable and consistent signal transitions.
- Clean separation of responsibilities.
- Easily extensible architecture.
- Maintainable and modular design.
- Suitable for simulation and real-time control.

---

## 🧩 UML Class Diagram

The system models traffic signals, states, and intersection control using a structured object-oriented design.

(Core relationships include TrafficLight, SignalState implementations, Intersection, and TrafficSignalController.)

---

## 🏗️ Core Classes, Interfaces, and Enumerations

### Direction (Enum)
Represents the directions at an intersection.

**Values**
- `NORTH`
- `SOUTH`
- `EAST`
- `WEST`

---

### SignalState (Interface)
Represents the state of a traffic light.

**Responsibilities**
- Define state-specific behavior
- Control signal transition logic
- Provide state identification

**Key Methods**
- `handle(TrafficLight, TrafficSignalController, Direction)`
- `getName()`

---

### GreenState
Concrete implementation of SignalState.

**Responsibilities**
- Allows traffic flow
- Transitions to YELLOW after configured duration

---

### YellowState
Concrete implementation of SignalState.

**Responsibilities**
- Warning transition state
- Transitions to RED after configured duration

---

### RedState
Concrete implementation of SignalState.

**Responsibilities**
- Stops traffic
- Triggers next direction in round-robin sequence

---

### TrafficLight
Represents a traffic signal for a specific direction.

**Key Properties**
- `direction`
- `currentState`

**Responsibilities**
- Maintain current signal state
- Delegate behavior to current state
- Support state transitions

---

### Intersection
Represents the full traffic intersection.

**Key Properties**
- `id`
- `signals`
- `signalDurations`
- `controller`

**Responsibilities**
- Initialize traffic signals
- Start signal cycling
- Provide manual override
- Provide signal access per direction

---

## 🧠 TrafficSignalController (Core Manager Class)

Acts as the **central orchestrator** of the traffic system.

### Responsibilities
- Manage signal scheduling
- Control state transitions
- Maintain round-robin direction order
- Fetch configured durations
- Handle manual overrides
- Coordinate all traffic lights

---

## 🧪 Demo / Driver Class

### TrafficSignalSystemDemo
Demonstrates system usage by:

- Configuring signal durations
- Creating traffic lights
- Starting signal cycling
- Performing manual override
- Simulating intersection behavior

This class helps visualize real-world traffic signal transitions.

---

## 🔒 Timing and Scheduling

- Signal transitions are controlled using a **scheduler or timer**.
- Each state runs for its configured duration.
- Automatic transitions occur without manual intervention.
- Manual override interrupts current scheduling safely.

---

## 🎯 Design Highlights

- State Pattern for signal behavior encapsulation
- Centralized traffic coordination
- Configurable timing per direction and state
- Manual override support
- Round-robin traffic control
- Highly extensible architecture
- Real-world simulation friendly
- Interview-ready LLD structure

---

## 🚀 Possible Extensions

- Adaptive traffic control using sensors
- Emergency vehicle priority handling
- Pedestrian crossing signals
- AI-based traffic optimization
- Real-time traffic analytics dashboard
- Distributed intersection coordination
- Traffic congestion prediction

---

## 📎 Conclusion

This design models a real-world traffic signal system with configurable timing, automatic transitions, and manual control.  
It demonstrates strong object-oriented design, clear separation of responsibilities, and practical use of the State Pattern.

The system is suitable for **LLD interviews**, **system design practice**, and **traffic simulation projects**.
