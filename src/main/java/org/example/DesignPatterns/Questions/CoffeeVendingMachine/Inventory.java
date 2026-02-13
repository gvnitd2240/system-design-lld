package org.example.DesignPatterns.Questions.CoffeeVendingMachine;

import org.example.DesignPatterns.Questions.CoffeeVendingMachine.enums.Ingredient;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Ingredient, Integer> stock;

    private static  Inventory instance;


    public static Inventory getInstance(){
        if(instance == null){
            synchronized (Inventory.class){
                if(instance == null){
                    instance = new Inventory();
                }
            }
        }

        return instance;
    }
    public Inventory() {
        this.stock = new HashMap<>();
    }


    public void addInventory(Ingredient ingredient, Integer quantity)
    {
        stock.put(ingredient, stock.getOrDefault(ingredient, 0)+1);
    }

    public void reduceStock(Map<Ingredient, Integer> map)
    {
        map.keySet().forEach((Ingredient k)->{
            if(map.get(k) >= stock.get(k)){
                stock.put(k, stock.getOrDefault(k, 0)-map.get(k));
                if(stock.get(k) == 0){
                    stock.remove(k);
                }
            }
        });
    }

    public boolean hasIngredients(Map<Ingredient, Integer> map){
        for (Map.Entry<Ingredient, Integer> entry : map.entrySet()) {

            Ingredient ingredient = entry.getKey();
            int needed = entry.getValue();

            int available = stock.getOrDefault(ingredient, 0);

            if (available < needed) {
                return false;
            }
        }

        return true;

    }

    public void printInventory(){
        for(Map.Entry<Ingredient, Integer> entry: stock.entrySet()){
            System.out.println("Ingredient " + entry.getKey().name() + " is available in quantity " + entry.getValue());
        }
    }

}
