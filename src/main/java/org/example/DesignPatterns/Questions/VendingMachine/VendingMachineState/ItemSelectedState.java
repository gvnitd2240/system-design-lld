package org.example.DesignPatterns.Questions.VendingMachine.VendingMachineState;

import org.example.DesignPatterns.Questions.VendingMachine.VendingMachineSystem;
import org.example.DesignPatterns.Questions.VendingMachine.enums.Coin;

public class ItemSelectedState extends VendingMachineState {


    protected ItemSelectedState(VendingMachineSystem vendingMachineSystem) {
        super(vendingMachineSystem);
    }

    @Override
    public void selectItem(String code) {
        System.out.println("Item is already selected.");
    }

    @Override
    public void insertCoin(Coin coin) {
        vendingMachineSystem.addBalance(coin.getValue());
        System.out.println("Coin Inserted: " + coin.getValue());

        double price = vendingMachineSystem.getSelectedItem().getPrice();

        if (vendingMachineSystem.getBalance() >= price) {
            System.out.println("Sufficient money received.");
            vendingMachineSystem.setVendingMachineState(new HasMoneyState(vendingMachineSystem));
        }

    }

    @Override
    public void dispense() {
        System.out.println("Please insert sufficient money.");
    }

    @Override
    public void refund() {
        this.vendingMachineSystem.reset();
        vendingMachineSystem.setVendingMachineState(new IdleMachineState(vendingMachineSystem));
    }
}
