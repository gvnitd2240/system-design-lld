package org.example.DesignPatterns.Questions.CoffeeVendingMachine.state;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.CoffeeVendingMachine;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;

public class ReadyState extends CoffeeVendingState{
    @Override
    public void insertMoney(CoffeeVendingMachine machine, int money) {
        System.out.println("Please select a coffee first.");
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {
        System.out.println("Please select a coffee first.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Nothing to cancel.");
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        machine.setSelectedCoffee(coffee);
        machine.setVendingState(new SelectingState());
        System.out.println(coffee.getCoffeeType() + " selected. Price: " + coffee.getPrice());
    }
}
