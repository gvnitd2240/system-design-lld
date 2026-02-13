package org.example.DesignPatterns.Questions.CoffeeVendingMachine.templatemethod;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators.Coffee;
import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;

import java.util.Map;

public class Capaccuino extends Coffee {
    public Capaccuino(){
        this.coffeeType = "Capaccuino";
    }

    @Override
    protected void addCondiments() {
        System.out.println("- Adding steamed milk and foam.");

    }

    @Override
    public int getPrice() {
        return 250;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        return Map.of(Ingredient.COFFEE_BEAN, 7, Ingredient.WATER, 30, Ingredient.MILK, 100);
    }
}
