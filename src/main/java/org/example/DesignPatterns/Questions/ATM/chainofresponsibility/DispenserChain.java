package org.example.DesignPatterns.Questions.ATM.chainofresponsibility;

public interface DispenserChain {

    boolean canDispense(int amount);

    void dispense(int amount);

    void setNextChain(DispenserChain nextChain);
}
