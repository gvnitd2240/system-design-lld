package org.example.DesignPatterns.Questions.CoffeeVendingMachine;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.CoffeeType;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.ToppingType;

import java.util.List;

public class CoffeeVendingMachineDemo {
    public static void main(String[] args) {
        CoffeeVendingMachine machine = CoffeeVendingMachine.getInstance();
        Inventory inventory = Inventory.getInstance();

        // --- Initial setup: Refill inventory ---
        System.out.println("=== Initializing Vending Machine ===");
        inventory.addInventory(Ingredient.COFFEE_BEAN, 50);
        inventory.addInventory(Ingredient.WATER, 500);
        inventory.addInventory(Ingredient.MILK, 200);
        inventory.addInventory(Ingredient.SUGAR, 100);
        inventory.addInventory(Ingredient.CARAMEL_SYRUP, 50);
        inventory.printInventory();

        // --- Scenario 1: Successful Purchase of a Latte ---
        System.out.println("\n--- SCENARIO 1: Buy a Latte (Success) ---");
        machine.selectCoffee(CoffeeType.LATTE, List.of());
        machine.insertMoney(200);
        machine.insertMoney(50); // Total 250, price is 220
        machine.dispenseCoffee();
        inventory.printInventory();

        // --- Scenario 2: Purchase with Insufficient Funds & Cancellation ---
        System.out.println("\n--- SCENARIO 2: Buy Espresso (Insufficient Funds & Cancel) ---");
        machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
        machine.insertMoney(100); // Price is 150
        machine.dispenseCoffee(); // Should fail
        machine.cancel(); // Should refund 100
        inventory.printInventory(); // Should be unchanged

        // --- Scenario 3: Attempt to Buy with Insufficient Ingredients ---
        System.out.println("\n--- SCENARIO 3: Buy Cappuccino (Out of Milk) ---");
        inventory.printInventory();
        machine.selectCoffee(CoffeeType.CAPPUCINO, List.of(ToppingType.CARAMEL_SYRUP, ToppingType.EXTRA_SUGAR));
        machine.insertMoney(300);
        machine.dispenseCoffee(); // Should fail and refund
        inventory.printInventory();

        // --- Refill and final test ---
        System.out.println("\n--- REFILLING AND FINAL TEST ---");
        inventory.addInventory(Ingredient.MILK, 200);
        inventory.printInventory();
        machine.selectCoffee(CoffeeType.LATTE, List.of(ToppingType.CARAMEL_SYRUP));
        machine.insertMoney(250);
        machine.dispenseCoffee();
        inventory.printInventory();
    }
}
