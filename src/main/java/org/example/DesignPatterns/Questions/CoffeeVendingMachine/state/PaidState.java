package org.example.DesignPatterns.Questions.CoffeeVendingMachine.state;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.CoffeeVendingMachine;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.Inventory;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;

public class PaidState extends CoffeeVendingState{
    @Override
    public void insertMoney(CoffeeVendingMachine machine, int money) {
        System.out.println("Already paid. Please wait for your coffee.");
    }

    @Override
    public void dispense(CoffeeVendingMachine machine) {
        Inventory inventory = Inventory.getInstance();
        Coffee coffeeToDispense = machine.getSelectedCoffee();

        if (!inventory.hasIngredients(machine.getSelectedCoffee().getRecipe())) {
            System.out.println("Sorry, out of ingredients for " + machine.getSelectedCoffee().getCoffeeType());
            machine.setVendingState(new OutOfIngridientState());
            machine.getVendingState().cancel(machine);
            return;
        }
        inventory.reduceStock(machine.getSelectedCoffee().getRecipe());

        coffeeToDispense.prepare();

        int change = machine.getMoneyInserted() - machine.getSelectedCoffee().getPrice();
        if (change > 0)
            System.out.println("Returning change: " + change);

        machine.reset();
        machine.setVendingState(new ReadyState());
    }

    @Override
    public void cancel(CoffeeVendingMachine machine) {
        new SelectingState().cancel(machine);
    }

    @Override
    public void selectCoffee(CoffeeVendingMachine machine, Coffee coffee) {
        System.out.println("Cannot select another coffee now.");
    }
}
