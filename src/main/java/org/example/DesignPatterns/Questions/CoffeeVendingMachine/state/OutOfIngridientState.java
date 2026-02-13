package org.example.DesignPatterns.Questions.CoffeeVendingMachine.state;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.CoffeeVendingMachine;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;

public class OutOfIngridientState extends CoffeeVendingState{
    @Override
    public void insertMoney(CoffeeVendingMachine machine, int money) {
        System.out.println("Sorry, the machine is out of ingredients. Money refunded.");
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {
        System.out.println("Sorry, the machine is out of ingredients.");
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        System.out.println("Refunding " + machine.getMoneyInserted());
        machine.reset();
        machine.setVendingState(new ReadyState());
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        System.out.println("Sorry, the machine is out of ingredients.");
    }
}
