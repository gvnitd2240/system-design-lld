package org.example.DesignPatterns.Questions.CoffeeVendingMachine.factory;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.CoffeeType;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.templatemethod.Capaccuino;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.templatemethod.Espresso;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.templatemethod.Latte;

public class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType coffeeType){
        switch(coffeeType){
            case LATTE -> {
                return new Latte();
            }
            case CAPPUCINO -> {
                return new Capaccuino();
            }
            case ESPRESSO -> {
                return new Espresso();
            }
            default -> {
                throw new IllegalArgumentException("No other coffee");
            }
        }
    }
}
