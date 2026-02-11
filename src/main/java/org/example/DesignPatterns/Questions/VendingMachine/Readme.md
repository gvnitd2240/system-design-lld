# 🥤 Designing a Vending Machine System

This project demonstrates the **Low Level Design (LLD)** of a **Vending Machine System** that supports multiple products, handles different payment denominations, manages inventory, and ensures consistent concurrent transactions.

The system is built using **object-oriented principles**, supports **state-based behavior**, and models real-world vending machine operations such as product selection, payment handling, dispensing, and change return.

---

## 📌 Requirements

### Functional Requirements

* The vending machine should support **multiple products** with different prices and quantities.
* The machine should accept **coins and notes** of different denominations.
* The machine should **dispense the selected product** and return change if necessary.
* The machine should **track available products and quantities**.
* The system should provide an interface for:

    * Restocking products
    * Collecting money
* The machine should handle exceptional scenarios:

    * Insufficient funds
    * Out-of-stock products
    * Invalid operations

### Non-Functional Requirements

* Handle **multiple transactions concurrently**.
* Ensure **data consistency and thread safety**.
* Follow **object-oriented design principles**.
* Be **modular and extensible**.
* Maintain clear **state-based behavior**.

---

## 🧩 UML Class Diagram

The following UML diagram illustrates the relationships between core entities such as products, inventory, machine states, and payment handling.

<p align="center">
  <a href="https://github.com/ashishps1/awesome-low-level-design/blob/main/class-diagrams/vendingmachine-class-diagram.png" target="_blank">
    <img src="https://raw.githubusercontent.com/ashishps1/awesome-low-level-design/main/class-diagrams/vendingmachine-class-diagram.png" width="850"/>
  </a>
</p>

---

## 🏗️ Core Classes, Interfaces, and Enumerations

### Product

Represents an item available in the vending machine.

**Key Properties**

* `name`
* `price`

**Responsibilities**

* Store product details
* Provide pricing information

---

### Coin (Enum)

Represents supported coin denominations accepted by the machine.

---

### Note (Enum)

Represents supported note denominations accepted by the machine.

---

### Inventory

Manages product storage and availability.

**Responsibilities**

* Maintain product quantities
* Support restocking
* Track stock updates
* Ensure thread-safe access using concurrent data structures

---

### VendingMachineState (Interface)

Defines behavior of the vending machine in different operational states.

**Responsibilities**

* Handle product selection
* Process payment
* Dispense product
* Return change
* Control state transitions

---

### IdleState

Default
