package org.example.DesignPatterns.Questions.CoffeeVendingMachine.decorators;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class CaramelSyrupDecorator extends CoffeeDecorator{
    private static final int COST = 30;
    private static final Map<Ingredient, Integer> RECIPE_ADDITION = Map.of(Ingredient.CARAMEL_SYRUP, 10);

    public CaramelSyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getCoffeeType() {
        return decoratedCoffee.getCoffeeType() + ", Extra Sugar";
    }

    @Override
    public int getPrice() {
        return decoratedCoffee.getPrice() + COST;
    }

    @Override
    public Map<Ingredient, Integer> getRecipe() {
        // Merge the recipes
        Map<Ingredient, Integer> newRecipe = new HashMap<>(decoratedCoffee.getRecipe());
        RECIPE_ADDITION.forEach((ingredient, qty) ->
                newRecipe.merge(ingredient, qty, Integer::sum));
        return newRecipe;
    }

    @Override
    public void prepare() {
        super.prepare();
        System.out.println("- Drizzling Caramel Syrup on top.");
    }
}
