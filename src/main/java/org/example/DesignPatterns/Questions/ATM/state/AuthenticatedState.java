package org.example.DesignPatterns.Questions.ATM.state;

import org.example.DesignPatterns.Questions.ATM.ATMSystem;
import org.example.DesignPatterns.Questions.ATM.enums.OperationType;

public class AuthenticatedState implements ATMState{
    @Override
    public void insertCard(ATMSystem atmSystem, String cardNumber) {
        System.out.println("Error: A card is already inserted and a session is active.");
    }

    @Override
    public void enterPin(ATMSystem atmSystem, String pin) {
        System.out.println("Error: PIN has already been entered and authenticated.");
    }

    @Override
    public void selectOperation(ATMSystem atmSystem, OperationType op, int amount) {
        switch (op){
            case DEPOSIT -> {
                if(amount <=0){
                    System.out.println("Error: Invalid Deposit amount specified.");
                    break;
                }
                System.out.println("Processing deposit for $" + amount);
                atmSystem.depositCash(amount);
                break;


            }
            case WITHDRAW -> {
                if(amount <=0){
                    System.out.println("Error: Invalid withdrawal amount specified.");
                    break;
                }

                double accountBalance = atmSystem.getBankService().getBalance(atmSystem.getCurrentCard());

                if(amount > accountBalance){
                    System.out.println("Error: Insufficient balance.");
                    break;
                }

                System.out.println("Processing withdrawal for $" + amount);
                // Delegate the complex withdrawal logic to the ATM's dedicated method
                atmSystem.withdrawCash(amount);
                break;
            }
            case CHECK_BALANCE -> {
                atmSystem.checkBalance();
                break;
            }
            default -> {
                System.out.println("Error: Invalid operation selected.");
                break;
            }
        }

        System.out.println("Transaction complete.");
        ejectCard(atmSystem);
    }

    @Override
    public void ejectCard(ATMSystem atmSystem) {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atmSystem.setCurrentCard(null);
        atmSystem.changeState(new IdleState());

    }
}
