package org.example.DesignPatterns.Questions.ATM;

import org.example.DesignPatterns.Questions.ATM.enums.OperationType;

public class ATMDemo {
    public static void main(String[] args) {
        ATMSystem atmSystem = ATMSystem.getInstance();

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.CHECK_BALANCE, 0); // $1000

        // Perform Withdraw Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.WITHDRAW, 570);

        // Perform Deposit Cash operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.DEPOSIT, 200);

        // Perform Check Balance operation
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.CHECK_BALANCE, 0); // $630

        // Perform Withdraw Cash more than balance
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("1234");
        atmSystem.selectOperation(OperationType.WITHDRAW, 700); // Insufficient balance

        // Insert Incorrect PIN
        atmSystem.insertCard("1234-5678-9012-3456");
        atmSystem.enterPin("3425");
    }
}
