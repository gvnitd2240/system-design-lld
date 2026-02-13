package org.example.DesignPatterns.Questions.CoffeeVendingMachine.state;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.CoffeeVendingMachine;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;

public class SelectingState extends CoffeeVendingState{
    @Override
    public void insertMoney(CoffeeVendingMachine machine, int amount) {
        machine.setMoneyInserted(machine.getMoneyInserted() + amount);
        System.out.println("Inserted " + amount + ". Total: " + machine.getMoneyInserted());
        if (machine.getMoneyInserted() >= machine.getSelectedCoffee().getPrice()) {
            machine.setVendingState(new PaidState());
        }
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {
        System.out.println("Already selected. Please pay or cancel.");

    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Transaction cancelled. Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setVendingState(new ReadyState());
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        System.out.println("Already selected. Please pay or cancel.");
    }
}
