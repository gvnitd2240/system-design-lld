package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class DispensingState extends VendingMachineState{
    protected DispensingState(VendingMachineSystem vendingMachineSystem) {
        super(vendingMachineSystem);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Currently dispensing. Please wait.");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Currently dispensing. Please wait.");
    }

    @Override
    public void dispense() {
        // already triggered by HasMoneyState
    }

    @Override
    public void refund() {

    }
}
