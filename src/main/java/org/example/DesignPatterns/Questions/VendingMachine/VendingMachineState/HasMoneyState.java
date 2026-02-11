package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class HasMoneyState extends VendingMachineState{

    protected HasMoneyState(VendingMachineSystem vendingMachineSystem) {
        super(vendingMachineSystem);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item already selected.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Already received full amount.");
    }

    @Override
    public void dispense() {
        vendingMachineSystem.setVendingMachineState(new DispensingState(vendingMachineSystem));
        vendingMachineSystem.dispatchItem();
    }

    @Override
    public void refund() {
        vendingMachineSystem.refundBalance();
        vendingMachineSystem.reset();
        vendingMachineSystem.setVendingMachineState(new IdleMachineState(vendingMachineSystem));
    }
}
