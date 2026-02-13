package org.example.DesignPatterns.Questions.ATM.state;

import org.example.DesignPatterns.Questions.ATM.ATMSystem;
import org.example.DesignPatterns.Questions.ATM.enums.OperationType;

public class HasCardState implements ATMState{
    @Override
    public void insertCard(ATMSystem atmSystem, String cardNumber) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATMSystem atmSystem, String pin) {
        System.out.println("Authenticating PIN...");
        boolean pinAuthenticated = atmSystem.authenticate(pin);
        if(pinAuthenticated){
            System.out.println("Authentication successful.");
            atmSystem.changeState(new AuthenticatedState());
        } else{
            System.out.println("Authentication failed: Incorrect PIN.");
            ejectCard(atmSystem);
        }
    }

    @Override
    public void selectOperation(ATMSystem atmSystem, OperationType op, int args) {
        System.out.println("Error: Please enter your PIN first to select an operation.");
    }

    @Override
    public void ejectCard(ATMSystem atmSystem) {
        System.out.println("Wrong Pin Added.");
        atmSystem.setAtmState(new IdleState());
        atmSystem.setCurrentCard(null);
    }
}
