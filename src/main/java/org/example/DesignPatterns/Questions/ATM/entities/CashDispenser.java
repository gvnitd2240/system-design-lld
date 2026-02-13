package org.example.DesignPatterns.Questions.ATM.entities;

import org.example.DesignPatterns.Questions.ATM.chainofresponsibility.DispenserChain;

public class CashDispenser {
    private final DispenserChain chain;


    public CashDispenser(DispenserChain chain) {
        this.chain = chain;
    }

    public synchronized boolean canDispense(int amount){
        if(amount % 10 != 0){
            return false;
        }
        return chain.canDispense(amount);
    }

    public synchronized void dispense(int amount){
        chain.dispense(amount);
    }
}
