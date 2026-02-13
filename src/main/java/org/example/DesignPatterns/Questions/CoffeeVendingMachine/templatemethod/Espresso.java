package org.example.DesignPatterns.Questions.CoffeeVendingMachine.templatemethod;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;

import java.util.Map;

public class Espresso extends Coffee {
    public Espresso(){
        this.coffeeType = "Espresso";
    }

    @Override
    protected void addCondiments() {
        System.out.println("- Adding steamed milk and foam.");

    }

    @Override
    public int getPrice() {
        return 300;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.COFFEE_BEAN, 7, Ingredient.WATER, 30);
    }
}
