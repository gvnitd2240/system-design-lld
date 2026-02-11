package org.example.DesignPatterns.Questions.VendingMachine.entities;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Integer> stockMap;
    private Map<String, Item> itemMap;

    public Inventory() {
        this.stockMap = new HashMap<>();
        this.itemMap = new HashMap<>();
    }

    public void addItem(String code, String name, double price, int quantity){
        stockMap.put(code, stockMap.getOrDefault(code, 0)+quantity);
        itemMap.put(code, new Item(code, name, price));
    }

    public void reduceStock(String code){
        if(!stockMap.containsKey(code)){
            throw new IllegalArgumentException("No product with code " + code + " found.");
        }

        stockMap.put(code, stockMap.getOrDefault(code, 0) -1);
        if(stockMap.get(code) == 0){
            stockMap.remove(code);
            itemMap.remove(code);
        }
    }

    public boolean isAvailable(String code){
        return stockMap.containsKey(code);
    }

    public Item getItem(String code){
        if(!stockMap.containsKey(code)){
            throw new IllegalArgumentException("No product with code " + code + " found.");
        }

        return itemMap.get(code);
    }
}
