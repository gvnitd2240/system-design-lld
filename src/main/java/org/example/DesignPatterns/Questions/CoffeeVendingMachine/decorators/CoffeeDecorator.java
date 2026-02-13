package org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;

import java.util.Map;

public class CoffeeDecorator extends Coffee{
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    public String getType(){
        return decoratedCoffee.getCoffeeType();
    }
    @Override
    protected void addCondiments() {
        decoratedCoffee.addCondiments();
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice();
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return decoratedCoffee.getRecipe();
    }
}
