package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.entities.Item;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class IdleMachineState extends VendingMachineState{

    public IdleMachineState(VendingMachineSystem vendingMachineSystem) {
        super(vendingMachineSystem);
    }

    @Override
    public void selectItem(String code) {
        if(!this.vendingMachineSystem.getInventory().isAvailable(code)){
            System.out.println("Item not available.");
            return;
        }

        this.vendingMachineSystem.setSelectedItemCode(code);
        this.vendingMachineSystem.setVendingMachineState(new ItemSelectedState(vendingMachineSystem));
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("No item selected.");
    }

    @Override
    public void dispense() {
        System.out.println("No item selected.");
    }

    @Override
    public void refund() {
        System.out.println("No money to refund.");
    }
}
