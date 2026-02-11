package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public abstract class VendingMachineState {

    protected VendingMachineSystem vendingMachineSystem;

    protected VendingMachineState(VendingMachineSystem vendingMachineSystem) {
        this.vendingMachineSystem = vendingMachineSystem;
    }

    public  abstract void selectItem(String code);

    public abstract void insertCoin(Coin coin);

    public abstract void dispense();

    public abstract void refund();

}
