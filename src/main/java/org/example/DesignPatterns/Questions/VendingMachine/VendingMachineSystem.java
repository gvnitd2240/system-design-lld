package org.example.DesignPatterns.Questions.VendingMachine;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState.IdleMachineState;
import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState.RefundState;
import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState.VendingMachineState;
import org.example.DesignPatterns.Questions.VendingMachine.entities.Inventory;
import org.example.DesignPatterns.Questions.VendingMachine.entities.Item;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class VendingMachineSystem {
    private static VendingMachineSystem instance;
    private VendingMachineState vendingMachineState;
    private Inventory inventory;
    private double balance;
    private String selectedItemCode;

    public VendingMachineSystem(){
        this.vendingMachineState = new IdleMachineState(this);
        this.inventory = new Inventory();
        this.balance = 0.0;
        this.selectedItemCode = null;
    }

    public static VendingMachineSystem getInstance(){
        if(instance == null){
            synchronized (VendingMachineSystem.class){
                if(instance == null){
                    instance = new VendingMachineSystem();
                }
            }
        }

        return instance;
    }

    public void addItem(String code, String name, double price, int quantity){
        inventory.addItem(code, name, price, quantity);
    }

    public void reduceStock(String code){
        inventory.reduceStock(code);
    }

    public void reset(){
        this.vendingMachineState = new IdleMachineState(this);
    }

    public Item getSelectItem(){
        if(selectedItemCode == null) throw new IllegalArgumentException("Item not yet selected.");
        return inventory.getItem(selectedItemCode);
    }

    public void selectItem(String code){
        if(!inventory.isAvailable(code)) throw new IllegalArgumentException("No Product with this code found.");
        this.vendingMachineState.selectItem(code);
    }

    public void insertCoin(Coin coin){
        this.vendingMachineState.insertCoin(coin);
    }

    public void dispatchItem(){
        Item item = inventory.getItem(selectedItemCode);
        if(balance>=item.getPrice()){
            inventory.reduceStock(item.getCode());
            balance -= item.getPrice();
            System.out.println("Dispensed: " + item.getName());
            if (balance > 0) {
//                System.out.println("Returning "+ balance);
//                balance = 0;

                this.setVendingMachineState(new RefundState(this));
                this.vendingMachineState.refund();
            } else {
                reset();
                setVendingMachineState(new IdleMachineState(this));
            }


        }
    }

    public void dispense() {
        vendingMachineState.dispense();
    }

    public void addBalance(double amount){
        balance+=amount;
    }

    public Item getSelectedItem(){
        return inventory.getItem(selectedItemCode);
    }


    public void refundBalance(){
        System.out.println("Refunding: " + balance);
        this.vendingMachineState.refund();

    }

    public void setSelectedItemCode(String selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public void setVendingMachineState(VendingMachineState vendingMachineState) {
        this.vendingMachineState = vendingMachineState;
    }

    public Inventory getInventory() { return inventory; }
    public double getBalance() { return balance; }
}
