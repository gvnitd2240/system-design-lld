package org.example.DesignPatterns.Questions.CoffeeVendingMachine.state;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.CoffeeVendingMachine;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;

public abstract class CoffeeVendingState {
    public abstract void insertMoney(CoffeeVendingMachine machine, int money);
    public abstract void dispense(CoffeeVendingMachine machine);
    public abstract void cancel(CoffeeVendingMachine machine);
    public abstract void selectCoffee(CoffeeVendingMachine machine, Coffee coffee);
}
