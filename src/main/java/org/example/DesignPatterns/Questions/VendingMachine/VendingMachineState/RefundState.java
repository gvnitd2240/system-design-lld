package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class RefundState extends VendingMachineState{
    public RefundState(VendingMachineSystem vendingMachineSystem) {
        super(vendingMachineSystem);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Refund in progress. Please wait.");

    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Refund in progress. Please wait.");

    }

    @Override
    public void dispense() {
        System.out.println("Refund in progress. Please wait.");
    }

    @Override
    public void refund() {
        double amount = vendingMachineSystem.getBalance();

        if (amount > 0) {
            System.out.println("Returning change: " + amount);
        }
        vendingMachineSystem.reset();
        vendingMachineSystem.setVendingMachineState(new IdleMachineState(vendingMachineSystem));
    }
}
